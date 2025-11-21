package com.hk.chart.service;

import com.hk.chart.entity.StockInfo;
import com.hk.chart.repository.StockInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
@RequiredArgsConstructor
public class KisMasterService {

    private final StockInfoRepository stockInfoRepository;

    // KIS 종목 마스터 파일 다운로드 URL (실전 투자 기준)
    private static final String KOSPI_MASTER_URL = "https://new.real.download.dws.co.kr/common/master/kospi_code.mst.zip";
    private static final String KOSDAQ_MASTER_URL = "https://new.real.download.dws.co.kr/common/master/kosdaq_code.mst.zip";

    @Transactional
    public void syncStockMasterData() {
        System.out.println(">>> [Master] 종목 마스터 데이터 동기화 시작...");
        
        try {
            // 1. 코스피 수집
            List<StockInfo> kospiList = downloadAndParse(KOSPI_MASTER_URL, "kospi_code.mst");
            System.out.println(">>> [Master] KOSPI 종목 수: " + kospiList.size());
            
            // 2. 코스닥 수집
            List<StockInfo> kosdaqList = downloadAndParse(KOSDAQ_MASTER_URL, "kosdaq_code.mst");
            System.out.println(">>> [Master] KOSDAQ 종목 수: " + kosdaqList.size());

            // 3. DB 저장 (기존 데이터가 있으면 업데이트하거나 무시)
            // saveAll은 ID가 같으면 update를 수행합니다.
            stockInfoRepository.saveAll(kospiList);
            stockInfoRepository.saveAll(kosdaqList);
            
            System.out.println(">>> [Master] DB 저장 완료.");

        } catch (Exception e) {
            System.err.println(">>> [Master] 동기화 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private List<StockInfo> downloadAndParse(String fileUrl, String fileName) throws IOException {
        List<StockInfo> list = new ArrayList<>();
        Path zipPath = Paths.get(fileName + ".zip");
        Path outPath = Paths.get(fileName);

        // 1. 파일 다운로드
        try (InputStream in = new URL(fileUrl).openStream()) {
            Files.copy(in, zipPath, StandardCopyOption.REPLACE_EXISTING);
        }

        // 2. 압축 해제
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath.toFile()))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                if (!zipEntry.isDirectory() && zipEntry.getName().equals(fileName)) {
                    Files.copy(zis, outPath, StandardCopyOption.REPLACE_EXISTING);
                    break;
                }
                zipEntry = zis.getNextEntry();
            }
        }

        // 3. 파일 파싱 (인코딩: CP949 필수)
        // KIS 마스터 파일은 텍스트가 아니라 '고정 바이트 길이'로 되어 있습니다.
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(outPath.toFile()), "CP949"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 라인의 바이트 길이 확인 (인코딩 문제 방지)
                byte[] lineBytes = line.getBytes("CP949");
                
                // KOSPI/KOSDAQ 구분 없이 공통적인 위치 파싱
                // (문서 기준: 단축코드 0~9(9자리), 한글명 21~? )
                // 실제 파일 구조: 단축코드(9자리) + ... + 한글명(...)
                
                try {
                    // 단축코드 (예: A005930 -> 앞의 A를 떼거나 그대로 사용)
                    // 보통 인덱스 0부터 9바이트가 단축코드 영역입니다.
                    // 실제 종목코드는 인덱스 1부터 7까지 (6자리)인 경우가 많습니다.
                    // 예: 'A' + '005930' + ...
                    
                    String rawCode = new String(lineBytes, 0, 9, "CP949").trim();
                    // 보통 "A005930" 처럼 옴. 실제 사용 코드는 뒤 6자리
                    String code = rawCode.length() >= 6 ? rawCode.substring(rawCode.length() - 6) : rawCode;

                    // 한글 종목명 (위치는 파일 포맷에 따라 다르지만 보통 21번째 바이트부터 시작)
                    // KOSPI 정규: 21byte 부터 40byte 길이
                    // KOSDAQ 정규: 21byte 부터 40byte 길이
                    String name = new String(lineBytes, 21, 40, "CP949").trim();
                    
                    // 4. 유효한 종목만 리스트에 추가 (이름이 없거나 코드가 이상하면 스킵)
                    if (!name.isEmpty() && code.length() == 6) {
                        list.add(new StockInfo(code, name));
                    }
                } catch (Exception e) {
                    // 파싱 에러난 라인은 스킵
                    continue;
                }
            }
        }
        
        // 임시 파일 삭제
        Files.deleteIfExists(zipPath);
        Files.deleteIfExists(outPath);

        return list;
    }
}