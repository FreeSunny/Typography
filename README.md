# Typography

##简介
主要实现了一个文本布局的功能

##效果图
主要实现了一个如下图所示的效果！有左右两列数据，右边的需要对齐，左边的数据长短不一致

![效果图](https://github.com/FreeSunny/Typography/blob/master/app/src/main/res/drawable-xxhdpi/sample_picture.png)

##实现方式
主要实现了以下几种方式

1. 采用自定义TextView的方式，重新onDraw函数

2. 方式2与方式1差不多，主要是所有计算也在onDraw函数中

3. 将数据源拼接成SpannableString，重写onDraw函数，根据内容draw每一个字符

4. 采用GridLayout方式实现，但是原始控件有展示问题，因此对此进行了修改

5. 采用每一行一个布局，手动一行一行进行添加

6. 方式与1差不多，但是不在继承TextView，而是直接继承View
