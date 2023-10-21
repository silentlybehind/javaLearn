# 120 最大收益股票收益问题

## 题目描述

假设知道某段连续时间内股票价格，计算通过买入卖出可获得的最大收益。

输入一个大小为`n`的数组$\text{price}=[p_1,p_2,p_3,p_4,\cdots,p_n]$，$p_i$是第`i`天的股要价格，$p_i$的格式为股票价格（非负整型）加上货币单位`Y`或者`S`，其中`Y`代表人民币，`S`代表美元，这里规定1美元可以兑换7元人民币。

$p_i$样例1：`123Y`代表123元人民币。

$p_i$样例2：`123S`代表123元美元，可兑换861元人民币。

假设可以在任何一天买入或者卖出胶票，也可以选择放弃交易，且手上只能拥有一支股票，请计算在交易周期`n`天内你能获得的最大收益（以人民币计算）。

## 输入描述

输入一个包含交易周期内各天股票价格的字符串，以空格分隔。不考虑异常输入情况。

## 输出描述

输出一个整型数，代表在交易周期`n`天内能获得的最大收益（以人民币计算），其中n < 10000。

备注: 股票价格只会用`Y`人民币或`S`美元进行输入，不考虑其他情况。

## 示例描述

### 示例一

**输入：**

```text
2Y 3S 4S 6Y 8S
```

**输出：**

```text
76
```

**说明：**

对应样例，在第1天买入，第3天卖出，第4天买入，第5天卖出。

## 解题思路

**基本思路：** 使用动态规划求解。

1. 将股票价格统一转换为人民币的值。
2. 动态规划计算最大利润：
    - 确定`dp`数组以及下标的含义：`dp[i][0]`表示第`i`天持有股票所得现金，`dp[i][1]`表示第`i`天不持有股票所得最多现金。
    - 确定递推公式：
        - 第i天持有股票所得现金=第i-1天持有股票与第i天买入股票的最大值：`dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] - prices[i])`
        - 第i天不持有股票所得最多现金=第i-1天不持有股票和第i天卖出股票的最大值：`dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] + prices[i])`
    - `dp`数组如何初始化：
        - `dp[0][0]`表示第0天持有股票所得现金，即买入股票，为`-prices[0]`。
        - `dp[0][1]`表示第0天不持有股票，为0。
    - 确定遍历顺序：从前先后。
3. 返回在交易周期`n`天内能获得的最大收益，即`dp[-1][1]`的值。

## 解题代码

```python
def convert_yuan(price: str):
    value = int(price[:-1])
    if price.endswith("S"):
        value *= 7
    return value


def solve_method(prices):
    prices = list(map(convert_yuan, prices))

    length = len(prices)
    # dp[i][0] 表示第i天持有股票所得现金。
    # dp[i][1] 表示第i天不持有股票所得最多现金
    dp = [[0] * 2 for _ in range(length)]
    dp[0][0] = -prices[0]
    dp[0][1] = 0
    for i in range(1, length):
        # 第i天持有股票所得现金=第i-1天持有股票与第i天买入股票的最大值
        dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] - prices[i])
        # 第i天不持有股票所得最多现金=第i-1天不持有股票和第i天卖出股票的最大值
        dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] + prices[i])
    return dp[-1][1]


if __name__ == '__main__':
    assert solve_method(["2Y", "3S", "4S", "6Y", "8S"]) == 76
```


