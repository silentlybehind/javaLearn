# 006 TLV编码

## 题目描述

TLV编码是按TagLengthValue格式进行编码的。一段码流中的信元用`tag`标识，`tag`在码流中唯一不重复，`length`表示信元`value`的长度，`value`表示信元的值。码流以某信元的`tag`开头，`tag`固定占一个字节，`length`固定占两个字节，字节序为小端序。

现给定tlv格式编码的码流以及需要解码的信元`tag`，请输出该信元的`value`。

其中：
- 输入码流的16进制字符中，不包括小写字母。
- 且要求输出的16进制字符串中也不要包含小写字母。
- 码流字符串的最大长度不超过50000个字节。

## 输入描述

输入第一行是第一个字符串，表示待解码信元的`tag`。

输入第二行是一个字符串，表示待解码的16进制码流，字节之间用空格分隔。

## 输出描述

输出一个字符串，表示待解码信元以16进制表示的`value`。

## 示例描述

### 示例一

**输入：**
```text
31
32 01 00 AE 90 02 00 01 02 30 03 00 AB 32 31 31 02 00 32 33 33 01 00 CC 
```

**输出：**
```text
32 33
```

**说明：**  

需要解码的信元的tag是31，

- 从码流的起始处开始匹配，`tag`为32的信元长度为1（`0100`，小端序表示为1）。
- 第二个信元的`tag`为90，其长度为2。
- 第三个信元的`tag`为30，其长度为3。
- 第四个信元的`tag`为31，其长度为2（`0200`）。

所以，返回长度后面的两个字节即可为`32 33`。

```
32 01 00 AE 
90 02 00 01 02 
30 03 00 AB 32 31 
31 02 00 32 33 
33 01 00 CC
```

## 解题思路

1. 初始化索引位置`index`。
2. 遍历码流：
    - 获取当前信元的`cur_tag`。
    - 按照小端序，组成信元的长度，并转换成十进制。
    - 如果当前信元的`cur_tag`是需要解码的信元`tag`，则获取对应长度的子码流，并返回结果。
    - 否则，将索引位置后移，继续遍历码流。

## 解题代码

```python
def solve_method(tag, source):
    index = 0
    while index < len(source):
        cur_tag = source[index]
        # 小端序
        length = int(source[index + 2] + source[index + 1], 16)
        if tag == cur_tag:
            value = source[index + 3: index + 3 + length]
            return value

        index += 3 + length


if __name__ == '__main__':
    source = ["32", "01", "00", "AE", "90", "02", "00", "01", "02", "30", "03", "00",
              "AB", "32", "31", "31", "02", "00", "32", "33", "33", "01", "00", "CC"]
    assert solve_method("31", source) == ["32", "33"]
```

