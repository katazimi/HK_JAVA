package com.hk.chart.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class ChartDataDto {
    private List<String> dates; // X축 (날짜)
    private List<Integer> prices; // Y축 (종가)
}