# 129 最近的点

## 题目描述

同一个数轴x有两个点的集合`A={A1,A2,...,Am}`和`B={B1,B2,...Bm}`，其中`A(i)`和`B(i)`均为正整数。A、B两个集合中的元素已经按照从小到大排好序，A、B均不为空。

给定一个距离`R`正整数，列出同时满足下列条件的的`(A(i),B(i))`数对。
1. A(i) <= B(i)
2. A(i)、B(i)之间距离小于等于`R`
3. 在满足1和2的情况下，每个A(i)只输出距离最近的B(i)
4. 输出结果按A(i)从小到大排序

## 输入描述

第1行为三个正整数：`m`、`n`、`R`

第2行有`m`个正整数，表示集合A

第3行有`n`个正整数，表示集合B

输入限制：
- 1 <= R <= 100000
- 1 <= n, m <= 100000
- 1 <= A(i), B(i) <= 1000000000

## 输出描述

每组数对输出一行A(i)和B(i)，以空格隔开

## 示例描述

### 示例一

**输入：**
```text
4 5 5
1 5 5 10
1 3 8 8 20
```

**输出：**
```text
1 1
5 8
5 8
```

## 解题思路

1. 遍历集合A的每个元素`a`和集合B的每个元素`b`
2. 如果`a <= b`且`b - a <= R`，则找到了一组满足条件的数对，存入结果列表中，并在集合B中删除元素`b`，停止遍历集合B。
3. 继续遍历集合A，重复步骤2。
4. 返回结果列表。

## 解题代码

```python
def solve_method(setA, setB, R):
    result = []
    for a in setA:
        for b in setB:
            if a <= b and b - a <= R:
                result.append([a, b])
                setB.remove(b)
                break

    return result


if __name__ == '__main__':
    setA = [1, 5, 5, 10]
    setB = [1, 3, 8, 8, 20]
    R = 5
    assert solve_method(setA, setB, R) == [[1, 1], [5, 8], [5, 8]]
```