<h1>SmartAndroidWidgets - Make the android widgets smart&EZ.</h1>

SmartAndroidWidgets是一个提供 一些让Android开发中一些复杂需求变得简单聪明的Widget的Library。目前提供了：

- **SmartLoadingLayout**：可用于Android中**界面加载时的不同状态切换显示**。
- **SmartPullableLayout**：一个可用于实现Android中**上拉加载、下拉刷新**的通用ViewGroup。

---

<h2>使用需知</h2>

- Required min SDK version : **API 11**。
-     compile 'me.rawnhwang.library:smart-android-widgets:1.0.0'

---

<h2>用例演示</h2>

<h3>SmartLoadingLayout</h3>

 SmartLoadingLayout是一个用于Android界面在进行加载时的不同的状态切换显示的Widget。这里的加载状态包括：

- 加载中
- 加载成功
- 加载错误
- 加载结果为空

目前这里提供了两种可选的Layout，分别是：

> - DefaultLoadingLayout (我们提供的SmartLoadingLayout默认实现，不要做额外的操作就可以直接使用)
> - CustomLoadingLayout(支持自定义 显示不同加载状态的View)

<h4>1.DefaultLoadingLayout用例演示 </h4>

DefaultLoadingLayout的使用非常简单，假设以下是我们的布局文件：

```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:id="@+id/iv_content"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@drawable/image_demo" />
</RelativeLayout>

```

以上例来说，布局中的ImageView就是我们在进入到这个界面时，如果加载成功想要显示的内容视图。这时我们要做的非常简单：
只要通过调用SmartLoadingLayout类的静态方法createDefaultLayout就可以得到一个现成的DefaultLoadingLayout对象。
在这个调用中我们只需将宿主Activity与作为内容视图的ImageView对象传递给createDefaultLayout就行了。

```
        ivContent = (ImageView) findViewById(R.id.iv_content);
        mLoadingLayout = SmartLoadingLayout.createDefaultLayout(this, ivContent);
```

就是这么简单，之后我们只需要根据不同的加载状态回调DefaultLoadingLayout对象的不同方法就搞定了：

- onLoading （加载中）
- onEmpty （加载结果为空）
- onError （加载出错）
- onDone （加载完成）

最后是DefaultLoadingLayout的演示效果：

![](https://github.com/RawnHwang/SmartAndroidWidgets/blob/master/ScreenShot/2.gif)

当然，如果您不喜欢DefaultLoadingLayout提供的默认效果。也可以通过提供的一系列自定义的方法接口来打造你心仪的效果：

```
defaultLoadingLayout.replaceLoadingProgress(); // 替换Loading状态界面的进度条View
defaultLoadingLayout.setLoadingDescription();  // 设置Loading文本提示
defaultLoadingLayout.setLoadingDescriptionColor(); // 设置Loading文本提示的文本颜色
defaultLoadingLayout.setLoadingDescriptionTextSize(); // 设置Loading文本提示的字体大小

defaultLoadingLayout.replaceEmptyIcon();   // 替换Empty状态界面的Icon提示
defaultLoadingLayout.setEmptyDescription(); // 设置Empty文本提示
defaultLoadingLayout.setEmptyDescriptionColor();  // 设置Empty文本提示的文本颜色
defaultLoadingLayout.setEmptyDescriptionTextSize(); // 设置Empty文本提示的字体大小

defaultLoadingLayout.replaceErrorIcon(); // 替换Error状态界面的Icon提示
defaultLoadingLayout.setErrorDescription(); // 设置Error文本提示
defaultLoadingLayout.setErrorDescriptionColor(); // 设置Error文本提示的文本颜色
defaultLoadingLayout.setErrorDescriptionTextSize(); // 设置Error文本提示的字体大小
defaultLoadingLayout.setErrorButtonText(); // 设置Error状态界面中的Button文本
defaultLoadingLayout.setErrorButtonTextColor(); // 设置Error状态界面中的Button文本颜色
defaultLoadingLayout.setErrorButtonBackground(); // 设置Error状态界面中的Button背景
defaultLoadingLayout.setErrorButtonListener();  // 为Error Button添加点击监听事件
defaultLoadingLayout.replaceErrorButton(); // 替换Error状态界面的Button
defaultLoadingLayout.hideErrorButton(); // 隐藏Error状态界面的Button
```

<h4>2.CustomLoadingLayout用例演示 </h4>

当然了，如果想要完全按照自己的喜好重新定义不同的状态显示View，则可以选择使用CustomLoadingLayout。

CustomLoadingLayout对象的构建方式与DefaultLoadingLayout类似，可以通过如下方式：

```
 mLoadingLayout = SmartLoadingLayout.createCustomLayout(this);
```

之后你可以通过完全按照自己的想法去定义不同状态的布局文件，比如：

custom_loading_view：
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ProgressBar
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true" />
</RelativeLayout>
```

custom_empty_view ：
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:text="这是我自定义的Empty View"/>
</RelativeLayout>
```

custom_error_view ：
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:text="这是我自定义的Error View"/>
</RelativeLayout>
```

之后我们将这些布局文件通过include的方式导入到我们的界面的布局文件中:

```
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:id="@+id/iv_content"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@drawable/image_demo" />

    <include
        android:id="@+id/custom_loading_view"
        layout="@layout/custom_loading_view"/>

    <include
        android:id="@+id/custom_empty_view"
        layout="@layout/custom_empty_view"/>

    <include
        android:id="@+id/custom_error_view"
        layout="@layout/custom_error_view"/>
</RelativeLayout>
```

最后不要忘记把对应状态下的view设置给CustomLoadingLayout，完成了这步工作，之后的调用就和DefaultLoadingLayout一致了。

上述用例的演示效果如下：

![](https://github.com/RawnHwang/SmartAndroidWidgets/blob/master/ScreenShot/3.gif)

---

<h3>SmartPullableLayout</h3>

- SmartPullableLayout是一个支持垂直方向拉动，并触发上拉、下拉事件的通用Widgets。
- SmartPullableLayout可以配合包括普通View，ScrollView，AbsListView，RecyclerView等等View使用。
- SmartPullableLayout内部已经处理了相关的滑动冲突，并且支持了嵌套滑动机制。

对于SmartPullableLayout的使用实际非常简单，因为其自身其实就是一个ViewGourp：

```
<?xml version="1.0" encoding="utf-8"?>
<me.hwang.widgets.SmartPullableLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/layout_pullable"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/rv_content"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</me.hwang.widgets.SmartPullableLayout>
```

之后我们只需要在对应的Activity(Fragment)中实例化SmartPullableLayout对象就可以了。当然别忘为SmartPullableLayout添加上拉动监听，就像下面这样：

```
        mPullableLayout.setOnPullListener(new SmartPullableLayout.OnPullListener() {
            @Override
            public void onPullDown() { // 下拉回调
                // do what you want do
            }

            @Override
            public void onPullUp() { // 上拉回调
                // do what you want do
            }
        });
```

需要记住的是！！！不要忘记在上（下）拉事件的回调成功执行完毕之后，调用stopPullBehavior方法结束（清楚）上/下拉动状态。

```
mPullableLayout.stopPullBehavior();
```

另外，SmartPullableLayout提供如下的几个视图属性供以使用：

```
<me.hwang.widgets.SmartPullableLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:library="http://schemas.android.com/apk/res-auto"
    android:id="@+id/layout_pullable"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    // 设置拉动部分的背景
    library:smart_ui_background="#ffffee" 
    // 是否启用下拉功能(true为启用,false为禁用)
    library:smart_ui_enable_pull_down="false"
    // 是否启用上拉功能(true为启用,false为禁用)
    library:smart_ui_enable_pull_up="false">
```


最后，是SmartPullableLayout配合不同View使用的演示效果：

![](https://github.com/RawnHwang/SmartAndroidWidgets/blob/master/ScreenShot/4.gif)

- 配合ScrollView使用的效果：

![](https://github.com/RawnHwang/SmartAndroidWidgets/blob/master/ScreenShot/5.gif)

- 配合ListView使用的效果：

![](https://github.com/RawnHwang/SmartAndroidWidgets/blob/master/ScreenShot/6.gif)

- 配合RecyclerView使用的效果：

![](https://github.com/RawnHwang/SmartAndroidWidgets/blob/master/ScreenShot/7.gif)
