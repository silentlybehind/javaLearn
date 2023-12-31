# 037 相同数字的积木游戏

## 题目描述

小华和小薇一起通过玩积木游戏学习数学。他们有很多积木，每个积木块上都有一个数字，积木块上的数字可能相同。

小华随机拿一些积木挨着排成一排，请小薇找到这排积木中数字相同且所处位置最远的2块积木块，计算他们的距离。

小薇请你帮忙替她解决这个问题。

## 输入描述

第一行输入为`N`，表示小华排成一排的积木总数。

接下来`N`行每行是一个数字，表示小花排成一排的积木上数字。

其中，取值范围是0 <= 积木上的数字 < 10^9，1 <= 积木长度 <= 10^5

## 输出描述

相同数字的积木的位置最远距离；如果所有积木数字都不相同，请返回-1。

## 示例描述

### 示例一

**输入：**
```text
5
1
2
3
1
4
```

**输出：**
```text
3
```

### 示例二

**输入：**
```text
2
1
2
```

**输出：**
```text
-1
```

## 解题思路

1. 创建索引字典，`key`为积木块上面的数字，`value`为积木的顺序位置。
2. 初始化索引字典。
3. 遍历索引字典中所有的`key`：
    - 如果一个数字上有多个位置，计算最远的位置距离。
    - 获取最远距离。
4. 返回最远距离，如果都不相同，则返回-1。

## 解题代码

```python
from collections import defaultdict


def solve_method(nums):
    idx = defaultdict(list)

    for i in range(len(nums)):
        idx[nums[i]].append(i)

    ans = -1
    for k in idx.keys():
        if len(idx[k]) > 1:
            ans = max(ans, idx[k][-1] - idx[k][0])

    return ans


if __name__ == '__main__':
    assert solve_method([1, 2, 3, 1, 4]) == 3
    assert solve_method([1, 2]) == -1
```