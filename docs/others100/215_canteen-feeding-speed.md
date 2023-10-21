# 215 食堂供餐

## 题目描述

某公司员工食堂以盒饭方式供餐。为了将员工取餐排队时间降低为0，食堂的供餐速度必须要足够快。现在需要根据以往员工取餐的统计信息，计算出一个刚好能达成拍段时间为0的最低供餐速度，即食堂在每个单位时间内必须做出多少份盒饭才能满足要求。

## 输入描述

第1行是一个正整数`N`，表示食堂开餐时长。其中，1 <= N <= 1000。

第2行是一个正整数`M`，表示开餐前，食堂已经准备好的盒饭份数。其中，P[i] <= M <= 1000。

第3行是`N`个正整数，用空格分隔，依次表示开餐时间内，按时间顺序每个单位时间进入食堂取餐的人数P[i]。其中 1 <= i <= N。

## 输出描述

输出一个整数，能满足题目要求的最低供餐速度（每个单位时间需要做出多少份盒饭）。

## 说明

- 每人只取一份盒饭。
- 需要满足排队时间为0，必须保证取餐员工到达食堂时，食堂库存盒饭数量不少于本次来取餐的人数。
- 第一个单位时间来取餐的员工只能取开餐前食堂准备好的盒饭。
- 每个单位时间里制作的盒饭，只能供应给后续单位时间来的取餐员工。
- 食堂在每个单位时间里制作的盒饭数量是相同的。

## 示例描述

### 示例一

**输入：**
```text
3
14
10 4 5
```

**输出：**
```text
3
```

**说明：**  
本示例中，总共有3批员工就餐，每批人数分别为10、4、5。开餐前食堂库存14份。食堂每个单位时间至少需要做出3份餐饭，才能达成排队时间为0的目标。

具体情况如下：
- 按照每个单位时间做出3份餐饭的速度：
    1. 第1个单位时间来的10位员工直接从库存取餐，取餐后，库存剩余4份盒饭，加上第1个单位时间做出的3份，库存有7份。
    2. 第2个单位时间来的4位员工从库存的7份中取4份，取餐后，库存剩余3份盒饭，加上第2个单位时间做出的3份，库存有6份。
    3. 第3个单位时间来的员工从库存的6份中取5份，库存足够。

- 按照每个单位时间做出2份餐饭的速度：
    1. 第1个单位时间来的10位员工直接从库存取餐，取餐后，库存剩余4份盒饭，加上第1个单位时间做出的2份，库存有6份。
    2. 第2个单位时间来的4位员工从库存的6份中取4份，取餐后，库存剩余2份盒饭，加上第2个单位时间做出的2份，库存有4份。 
    3. 第3个单位时间来的员工需要取5份，但库存只有4份，库存不够。

所以，食堂每个单位时间至少需要做出3份餐饭，才能达成排队时间为0的目标。    

## 解题思路

**基本思路：** 使用二分查找，检查供餐速度是否满足，逐步缩小待检查的范围。
1. 初始化最小和最大供餐速度。
2. 使用二分查找，速度范围为[min_speed, max_speed]
   - 获取中间的速度
   - 检查该速度是否能满足：
      - 如果能满足，继续查找较小的速度。
      - 如果不能满足，继续查找较大的速度。
3. 返回适合的速度。

## 解题代码

```python
def solve_method(total, M, N, P):
    min_speed, max_speed = 0, total - M
    result = max_speed
    # 二分查找，速度范围为[min_speed, max_speed]
    while min_speed <= max_speed:
        mid = min_speed + (max_speed - min_speed) // 2
        # 检查该速度是否能满足
        if check_speed(mid, M, N, P):
            # 如果能满足
            result = mid
            # 继续查找左侧
            max_speed = mid - 1
        else:
            # 如果不能，则继续查找右侧
            min_speed = mid + 1

    return result


def check_speed(speed, foods, N, P):
    for i in range(N):
        foods -= P[i]
        if foods < 0:
            return False
        foods += speed
    return True


if __name__ == '__main__':
    P = [10, 4, 5]
    assert solve_method(sum(P), 14, 3, P) == 3
```