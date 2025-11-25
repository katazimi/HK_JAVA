package com.hk.chart.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.hk.chart.config.KisApiConfig;
import com.hk.chart.dto.response.DailyPriceDataDto;
import com.hk.chart.dto.response.KisDailyPriceResponse;
import com.hk.chart.entity.StockCandle;
import com.hk.chart.repository.StockCandleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KisMarketService {

	private final KisApiConfig kisApiConfig;
    private final WebClient kisWebClient;
    private final KisAuthService authService; // 토큰 관리를 위해 주입
    private final StockCandleRepository candleRepository;
    
    private static final String TR_ID_PERIOD_PRICE = "FHKST03010100";

    // 거래 ID (Daily Price Inquiry): FHKST01010400
    private static final String TR_ID_DAILY_PRICE = "FHKST01010400";

    /**
     * 특정 종목의 과거 일봉 데이터를 조회합니다.
     * @param stockCode 조회할 종목 코드 (예: "005930")
     * @param period 기간 구분 (D:일봉, W:주봉, M:월봉)
     * @return 일봉 데이터 리스트
     */
    public List<DailyPriceDataDto> getDailyPriceData(String stockCode, String period) {
        
        // 1. 접근 토큰 획득
        String accessToken = authService.getAccessToken();

        // 2. WebClient를 사용한 GET 요청 실행
        KisDailyPriceResponse response = kisWebClient.get()
                .uri(uriBuilder -> uriBuilder
                    // API 경로 설정
                    .path("/uapi/domestic-stock/v1/quotations/inquire-daily-price")
                    // 쿼리 파라미터 설정
                    .queryParam("FID_COND_MRKT_DIV_CODE", "J") // 조건 시장 구분 코드 (J: 주식)
                    .queryParam("FID_INPUT_ISCD", stockCode)    // 입력 종목 코드
                    .queryParam("FID_PERIOD_DIV_CODE", period)  // 기간 구분 코드 (D, W, M)
                    .queryParam("FID_ORG_ADJ_PRC", "1")         // 수정주가 반영 여부 (1: 반영)
                    .build())
                // 3. 헤더 설정 (토큰, App Key/Secret은 WebClientConfig에서 설정했으나, 토큰은 여기서 수동 추가)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header("appkey", kisApiConfig.getAppKey()) // WebClientConfig에서 Default로 설정하지 않았다면 여기서 추가
                .header("appsecret", kisApiConfig.getAppSecret())
                .header("tr_id", TR_ID_DAILY_PRICE)
                .retrieve()
                .bodyToMono(KisDailyPriceResponse.class)
                .block();

        if (response != null && "0".equals(response.getRt_cd())) {
            return response.getOutput();
        } else if (response != null) {
            System.err.println("KIS API Error: " + response.getMsg1());
            throw new RuntimeException("주가 데이터 조회 실패: " + response.getMsg1());
        }
        return List.of();
    }
    
    /**
     * 특정 기간(시작일~종료일)의 주가 데이터를 조회합니다.
     * API: 국내주식 기간별 시세 조회 (inquire-daily-itemchartprice)
     * * @param stockCode 종목코드 (005930)
     * @param startDate 시작일 (YYYYMMDD)
     * @param endDate   종료일 (YYYYMMDD)
     * @return 일봉 데이터 리스트
     */
    public List<DailyPriceDataDto> getDailyPriceDataWithDate(String stockCode, String startDate, String endDate) {
        
        String accessToken = authService.getAccessToken();

        KisDailyPriceResponse response = kisWebClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/uapi/domestic-stock/v1/quotations/inquire-daily-itemchartprice") // ⭐️ 경로 변경됨
                    .queryParam("FID_COND_MRKT_DIV_CODE", "J")
                    .queryParam("FID_INPUT_ISCD", stockCode)
                    .queryParam("FID_INPUT_DATE_1", startDate) // ⭐️ 시작일 파라미터
                    .queryParam("FID_INPUT_DATE_2", endDate)   // ⭐️ 종료일 파라미터
                    .queryParam("FID_PERIOD_DIV_CODE", "D")    // 기간 (D:일봉)
                    .queryParam("FID_ORG_ADJ_PRC", "1")        // 수정주가 반영
                    .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header("appkey", kisApiConfig.getAppKey())
                .header("appsecret", kisApiConfig.getAppSecret())
                .header("tr_id", TR_ID_PERIOD_PRICE) // ⭐️ TR_ID 변경됨 (FHKST03010100)
                .retrieve()
                .bodyToMono(KisDailyPriceResponse.class)
                .block();

        if (response != null && "0".equals(response.getRt_cd())) {
            // 기간별 조회 API는 결과가 'output2'에 담겨 옵니다.
            return response.getOutput2() != null ? response.getOutput2() : List.of();
        } else if (response != null) {
            System.err.println("KIS API Error (Period): " + response.getMsg1());
            // 에러 발생 시 빈 리스트 반환하거나 예외 던짐
            return List.of();
        }
        return List.of();
    }
    
    /**
     * 주기(일/주/월)에 따라 캔들 데이터를 조회하거나 가공해서 반환합니다.
     */
    public List<StockCandle> getCandleDataByPeriod(String stockCode, String period, int limit) {
        // 1. 일봉은 DB에서 바로 가져옴 (최근 데이터 넉넉하게)
        // 주봉/월봉 계산을 위해 일봉 데이터를 충분히 많이 가져옵니다 (limit * 5 ~ 30)
        int fetchLimit = period.equals("D") ? limit : limit * 30;
        
        List<StockCandle> dailies = candleRepository.findRecentCandles(stockCode, fetchLimit);
        // 날짜 오름차순(과거->현재) 정렬
        Collections.reverse(dailies);

        if (period.equals("D")) {
            // 일봉은 이미 MA가 계산되어 있으므로 그대로 반환 (최근 N개만 자름)
            int size = dailies.size();
            return size > limit ? dailies.subList(size - limit, size) : dailies;
        }

        // 2. 주봉/월봉으로 데이터 합치기 (Resampling)
        List<StockCandle> aggregated = aggregateCandles(dailies, period);

        // 3. 이동평균선(MA) 실시간 재계산 (주/월봉용)
        calculateDynamicMA(aggregated);

        // 4. 요청한 개수만큼 자르기
        int size = aggregated.size();
        return size > limit ? aggregated.subList(size - limit, size) : aggregated;
    }

    // 캔들 병합 로직 (핵심)
    private List<StockCandle> aggregateCandles(List<StockCandle> dailies, String period) {
        List<StockCandle> result = new ArrayList<>();
        if (dailies.isEmpty()) return result;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        StockCandle currentCandle = null;
        String currentPeriodKey = ""; // 주차(Week) 또는 월(Month) 식별자

        for (StockCandle day : dailies) {
            LocalDate date = LocalDate.parse(day.getDate(), formatter);
            
            // 그룹 키 생성 (주봉: YYYY-wWW, 월봉: YYYY-MM)
            String key;
            if (period.equals("W")) {
                int weekOfYear = date.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
                key = date.getYear() + "-w" + weekOfYear;
            } else { // "M"
                key = date.getYear() + "-" + date.getMonthValue();
            }

            if (!key.equals(currentPeriodKey)) {
                // 새로운 주기 시작 -> 이전 캔들 저장
                if (currentCandle != null) result.add(currentCandle);
                
                // 새 캔들 초기화
                currentCandle = StockCandle.builder()
                        .stockCode(day.getStockCode())
                        .date(day.getDate()) // 시작일 (나중에 종료일로 갱신)
                        .open(day.getOpen())
                        .high(day.getHigh())
                        .low(day.getLow())
                        .close(day.getClose())
                        .volume(day.getVolume())
                        .build();
                currentPeriodKey = key;
            } else {
                // 기존 주기에 데이터 병합 (고가, 저가, 종가, 거래량 갱신)
                if (currentCandle != null) {
                    currentCandle = StockCandle.builder()
                            .stockCode(currentCandle.getStockCode())
                            .date(day.getDate()) // 날짜는 해당 주기의 마지막 날짜로 계속 업데이트
                            .open(currentCandle.getOpen()) // 시가는 유지
                            .high(Math.max(currentCandle.getHigh(), day.getHigh()))
                            .low(Math.min(currentCandle.getLow(), day.getLow()))
                            .close(day.getClose()) // 종가는 최신값
                            .volume(currentCandle.getVolume() + day.getVolume()) // 거래량 누적
                            .build();
                }
            }
        }
        // 마지막 캔들 추가
        if (currentCandle != null) result.add(currentCandle);
        
        return result;
    }

    // 동적 MA 계산 (주봉/월봉용)
    private void calculateDynamicMA(List<StockCandle> candles) {
        for (int i = 0; i < candles.size(); i++) {
            Integer ma5 = calcAvg(candles, i, 5);
            Integer ma20 = calcAvg(candles, i, 20);
            Integer ma60 = calcAvg(candles, i, 60);
            candles.get(i).setMa(ma5, ma20, ma60);
        }
    }

    private Integer calcAvg(List<StockCandle> list, int idx, int period) {
        if (idx < period - 1) return null;
        long sum = 0;
        for (int j = 0; j < period; j++) sum += list.get(idx - j).getClose();
        return (int) (sum / period);
    }
    
    @Transactional
    public void collectAllHistory(String stockCode) {
        // 1. 수집 종료 기준일 (삼성전자 상장일 이전 등 충분히 과거)
        String targetStartDate = "19900101"; 
        
        // 2. 수집 시작일 (오늘부터 과거로 감)
        LocalDate currentEndDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        System.out.println(">>> [" + stockCode + "] 과거 데이터 수집 시작...");

        boolean isRunning = true;
        while (isRunning) {
            try {
                String endDateStr = currentEndDate.format(formatter);
                // ⭐️ [핵심] 한 번에 100일 치만 요청 (API 제한 고려)
                String startDateStr = currentEndDate.minusDays(100).format(formatter);

                // 수집 목표 날짜보다 더 과거로 가면 종료
                if (startDateStr.compareTo(targetStartDate) < 0) {
                    System.out.println(">>> 목표 날짜(" + targetStartDate + ") 도달. 수집 종료.");
                    break;
                }

                System.out.println(">>> 요청 기간: " + startDateStr + " ~ " + endDateStr);

                // 3. API 호출
                List<DailyPriceDataDto> dataList = getDailyPriceDataWithDate(stockCode, startDateStr, endDateStr);

                if (dataList == null || dataList.isEmpty()) {
                    System.out.println(">>> 데이터 없음 (휴장일 또는 상장 이전). 계속 진행...");
                    // 데이터가 없어도 날짜는 과거로 이동해야 함
                    currentEndDate = currentEndDate.minusDays(100);
                    continue;
                }

                // 4. DB 저장
                int saveCount = 0;
                for (DailyPriceDataDto dto : dataList) {
                    // 날짜 포맷 등 유효성 체크
                    if (dto.getStck_bsop_date() == null) continue;

                    // 중복 확인 후 저장
                    if (!candleRepository.existsByStockCodeAndDate(stockCode, dto.getStck_bsop_date())) {
                        StockCandle candle = StockCandle.builder()
                                .stockCode(stockCode)
                                .date(dto.getStck_bsop_date())
                                .open(parseSafeInt(dto.getStck_oprc()))
                                .high(parseSafeInt(dto.getStck_hgpr()))
                                .low(parseSafeInt(dto.getStck_lwpr()))
                                .close(parseSafeInt(dto.getStck_clpr()))
                                .volume(parseSafeLong(dto.getAcc_trd_qty())) 
                                .build();
                        candleRepository.save(candle);
                        saveCount++;
                    }
                }
                
                System.out.println(">>> " + saveCount + "건 저장 완료.");

                // 5. 다음 요청 준비: 이번 요청의 시작일 하루 전날을 다음 요청의 종료일로 설정
                // (API가 100일치를 다 안 줄수도 있으므로, 단순히 날짜 계산으로 이동)
                currentEndDate = currentEndDate.minusDays(101);

                // 6. ⭐️ API 호출 제한 방지 (필수)
                Thread.sleep(300); // 0.3초 대기

            } catch (Exception e) {
                System.err.println(">>> 수집 중 에러 발생: " + e.getMessage());
                e.printStackTrace();
                isRunning = false; // 에러 시 루프 중단
            }
        }
        calculateMovingAverage(stockCode);
        System.out.println(">>> [" + stockCode + "] 수집 프로세스 종료.");
    }
    
    /**
     * 특정 종목의 데이터를 최신 상태로 업데이트 (스케줄러용)
     */
    @Transactional
    public void updateStockData(String stockCode) {
        // 1. DB에서 가장 마지막 저장된 날짜 가져오기
        String lastDate = candleRepository.findLatestDate(stockCode);
        
        if (lastDate == null) {
            // 데이터가 아예 없으면 전체 수집 실행
            collectAllHistory(stockCode);
            return;
        }

        // 2. 수집할 기간 설정 (마지막 저장일 다음날 ~ 오늘)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate startDate = LocalDate.parse(lastDate, formatter).plusDays(1); // 다음날
        LocalDate today = LocalDate.now();

        // 이미 최신 데이터라면 종료
        if (startDate.isAfter(today)) {
            System.out.println(">>> [" + stockCode + "] 이미 최신 데이터입니다.");
            return;
        }

        String strStart = startDate.format(formatter);
        String strEnd = today.format(formatter);

        System.out.println(">>> [" + stockCode + "] 업데이트 시작 (" + strStart + " ~ " + strEnd + ")");

        // 3. API 호출 (기간별 조회)
        try {
            List<DailyPriceDataDto> dataList = getDailyPriceDataWithDate(stockCode, strStart, strEnd);
            
            if (dataList == null || dataList.isEmpty()) {
                System.out.println(">>> [" + stockCode + "] 신규 데이터 없음.");
                return;
            }

            // 4. DB 저장
            int count = 0;
            for (DailyPriceDataDto dto : dataList) {
                if (!candleRepository.existsByStockCodeAndDate(stockCode, dto.getStck_bsop_date())) {
                    StockCandle candle = StockCandle.builder()
                            .stockCode(stockCode)
                            .date(dto.getStck_bsop_date())
                            .open(parseSafeInt(dto.getStck_oprc()))
                            .high(parseSafeInt(dto.getStck_hgpr()))
                            .low(parseSafeInt(dto.getStck_lwpr()))
                            .close(parseSafeInt(dto.getStck_clpr()))
                            .volume(parseSafeLong(dto.getAcc_trd_qty()))
                            .build();
                    candleRepository.save(candle);
                    count++;
                }
            }
            calculateMovingAverage(stockCode);
            System.out.println(">>> [" + stockCode + "] " + count + "건 업데이트 완료.");
            
        } catch (Exception e) {
            System.err.println(">>> [" + stockCode + "] 업데이트 중 오류: " + e.getMessage());
        }
    }
    
    /**
     * 특정 종목의 모든 데이터에 대해 이동평균선(MA)을 계산하고 DB에 업데이트합니다.
     */
    @Transactional
    public void calculateMovingAverage(String stockCode) {
        // 1. 전체 데이터를 날짜 오름차순(과거->현재)으로 조회
        List<StockCandle> candles = candleRepository.findAllByStockCodeOrderByDateAsc(stockCode);
        
        if (candles.isEmpty()) return;

        System.out.println(">>> [" + stockCode + "] 이동평균선 계산 시작 (" + candles.size() + "건)...");

        // 2. 반복문을 돌며 MA 계산
        for (int i = 0; i < candles.size(); i++) {
            Integer ma5 = calculateAvg(candles, i, 5);
            Integer ma20 = calculateAvg(candles, i, 20);
            Integer ma60 = calculateAvg(candles, i, 60);

            // Entity에 값 설정 (JPA의 Dirty Checking으로 인해 save 호출 없어도 트랜잭션 끝나면 자동 업데이트됨)
            candles.get(i).setMa(ma5, ma20, ma60);
        }
        
        System.out.println(">>> [" + stockCode + "] 이동평균선 계산 완료.");
    }

    // 평균 계산 헬퍼 메서드
    private Integer calculateAvg(List<StockCandle> list, int currentIndex, int period) {
        if (currentIndex < period - 1) {
            return null; // 데이터 부족 시 null (그리지 않음)
        }

        long sum = 0;
        for (int j = 0; j < period; j++) {
            sum += list.get(currentIndex - j).getClose();
        }
        return (int) (sum / period);
    }
    
    private int parseSafeInt(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    private long parseSafeLong(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0L;
        }
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            return 0L;
        }
    }
}
