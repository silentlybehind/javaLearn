# 210 阿里巴巴找黄金宝箱（4）

## 题目描述

一贫如洗的樵夫阿里巴巴在去砍柴的路上，无意中发现了强盗集团的藏宝地，藏宝地有编号从0\~N的箱子，每个箱子上面贴有一个数字。箱子排成了一个环，编号最大的箱子的下一个是编号为0的箱子。

请输出每个箱子贴了数字之后的第一个比它大的数，如果不存在，则输出-1。

## 输入描述

第1行输入一个数字串，数字之间使用`,`分隔，例如：

```text
1,2,3,6,3
```

其中 1 <= 个数 <= 100000，1 <= 每个数字 <= 10000。

## 输出描述

下一个大的数的列表，以`,`分隔，例如：

```text
2,3,6,-1,6
```

## 示例描述

### 示例一

**输入：**
```text
2,5,2
```

**输出：**
```text
5,-1,5
```

**说明：**  
第一个2的下一个更大的数是数字5，数字5找不到下一个更大的数，第2个2的下一个最大的数需要循环搜索，结果也是5。

### 示例二

**输入：**
```text
3,4,5,6,3
```

**输出：**

```text
4,5,6,-1,4
```

## 解题思路

**基本思路：** 按照循环列表的思路解题。

1. 初始化当前的遍历位置`index`
2. 遍历输入的列表：
   - 初始化内部遍历的位置`j`
   - 当`j`达到了最后一个位置，重新回到开始位置
   - 开始循环遍历列表：
        - 如果找到了比当前数大的数，将该数存入结果列表。则保存当前遍历的位置
        - 如果循环一圈都没有找到，则将-1存入结果列表，并且记录下一个位置
    
3. 返回结果列表。

## 解题代码

```python
def solve_method(nums):
    res = [0] * len(nums)
    # 当前的遍历位置
    index = 0
    for i in range(len(nums)):
        num = nums[i]
        # j为内部遍历的位置
        j = index + 1
        # 当j达到了最后一个位置，重新回到开始位置
        if j == len(nums):
            j = 0
        while index != j:
            # 如果没有循环一圈
            if num < nums[j]:
                res[i] = nums[j]
                # 保存当前遍历的位置
                index = j
                break
            # 继续遍历
            j += 1
            # 当j达到了最后一个位置，重新回到开始位置
            if j == len(nums):
                j = 0
        # 如果循环一圈都没有找到比当前数大的值，输出-1
        if res[i] == 0:
            res[i] = -1
            # 记录下一个位置
            index = i + 1

    return res


if __name__ == '__main__':
    assert solve_method([1, 2, 3, 6, 3]) == [2, 3, 6, -1, 6]
    assert solve_method([2, 5, 2]) == [5, -1, 5]
    assert solve_method([3, 4, 5, 6, 3]) == [4, 5, 6, -1, 4]
```