package com.softeem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// 代表金额的 账户值对象
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceInfo {
    private Long userId;
    private Long balance;
}
