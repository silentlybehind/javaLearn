# 067 寻找连续区间

## 题目描述

给定一个含有`N`个正整数的数组，求出有多少个连续区间（包括单个正整数），它们的和大于等于`x`。

## 输入描述

第一行两个整数`N`与`x`，其中0 < N <= 100000、0 <= x <= 10000000。

第二行有`N`个正整数，每个正整数小于等于100。

## 输出描述

输出一个整数，表示所求的个数。

## 示例描述

### 示例一

**输入：**
```text
3 7
3 4 7
```

**输出：**
```text
4
```

**说明：**

3+4、4+7、3+4+7、7这四组数据都是大于等于7的，所以输出为4。

### 示例二

**输入：**
```text
10 10000000
1 2 3 4 5 6 7 8 9 10
```

**输出：**
```text
0
```

## 解题思路

1. 如果整个数组之和小于目标值，则直接返回0。
2. 初始化计数器`count`。   
3. 遍历整个数组：
    - 如果子数组之和大于等于目标值，计数器累加1。
4. 返回结果，即计数器的值。    

## 解题代码

```python
def solve_method(nums, target):
    n = len(nums)
    count = 0
    if sum(nums) < target:
        return count
    for i in range(n):
        for j in range(i, n):
            if sum(nums[i:j + 1]) >= target:
                count += 1
    return count


if __name__ == '__main__':
    assert solve_method([3, 4, 7], 7) == 4
    assert solve_method([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], 10000) == 0
```
