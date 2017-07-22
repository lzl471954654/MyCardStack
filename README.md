# CardStackView使用教程
***
最近在github上面看到一个挺不错的第三方开源控件CardStackView，效果非常的酷炫。但是原作者的Demo比较简单，并没有太详细的说明，所以我研究了一下使用方法，写了下来。

下面是官方效果图：

![CardStackView官方效果图](http://note.youdao.com/yws/api/personal/file/B0EF130627684B2AA4F80E40BBF607A2?method=download&shareKey=baae04d0db4fc7c8016ebb3b18749d49)![CardStackView官方效果图](http://note.youdao.com/yws/api/personal/file/0660E05E27F24E70B830DE3232AB605C?method=download&shareKey=e792b04929c04b72bb503360ffdffe4c)![CardStackView官方效果图](http://note.youdao.com/yws/api/personal/file/9EE0872D7659490AB0424E79BC03D6E2?method=download&shareKey=40333d5bf54c404247823621c67b194a)

下面是我自己Demo的效果：

![Demo效果](http://note.youdao.com/yws/api/personal/file/934F5D562DF143899CDF3799F203048B?method=download&shareKey=337e4150daa106741700c4d7f28d7a59)

***
## 使用步骤
- 第一步导包
- 第二步在布局中创建CardStackView
- 第三步为CardStackView中的Item创建布局
- 第四步为CardStackView自定义Adapter
- 第五步在Activity中绑定CardStackView并为其绑定Adapter

***

### 1-导入CardStackView包
在Gradle中的dependencies中加入
> compile 'com.loopeer.library:cardstack:1.0.2'

这样就完成了导包


### 2-在布局中创建CardStackView

代码如下：
```xml
<com.loopeer.cardstack.CardStackView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/cardStackView"
        >
</com.loopeer.cardstack.CardStackView>
```

### 3-为CardStackView中的Item创建布局
Item效果如下：

![Item效果图](http://note.youdao.com/yws/api/personal/file/80A32DA5265F48378E4D547470F32C1C?method=download&shareKey=15fbc478b6c33b9ebc47516d1cd416f9)

最上层的部分是这个卡片的Head部分，Head中有背景颜色，还有标题，Head的下面是一个RecyclerView，用来显示一些列表数据。

下面是XML代码：scores_card_item.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp"
    >

    <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="80dp"
        android:background="@drawable/scores_card_coners"
        android:id="@+id/card_title"
        >
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="2016-2017-2学期"
            android:layout_margin="16dp"
            android:textSize="20sp"
            android:textStyle="bold"
            android:textColor="#ffffff"
            android:id="@+id/card_title_text"
            />
    </FrameLayout>
    <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <android.support.v7.widget.RecyclerView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:id="@+id/scores_list"
            android:layout_marginStart="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginBottom="16dp"
            >
        </android.support.v7.widget.RecyclerView>
    </FrameLayout>

</LinearLayout>
```
我自己实现的Demo较简单，卡片的样式只有一种，其实CardStackView其中的卡片样式可以是多种的，这点和RecyclerView一样，其中的每一项都可以不一样。











### 4-为CardStackView自定义Adapter
CardStackView使用的核心就是为这个Adapter，他为CardStackView提供数据，让CardStackView显示对应的数据和颜色，在这里CardStackView的Adapter和RecyclerView的Adapter非常的像。

其中导入的包中已经有两种自带的Adapter
* abstract class Adapter<VH extends ViewHolder>
* abstract class StackAdapter<T> extends CardStackView.Adapter<CardStackView.ViewHolder>

第一个Adapter，是CardStackView.Adapter，它是最基础的adapter，如果你有比较复杂的需求可以自己继承这个最基础的adapter去自定义。

第二个StackAdapter<T>是继承自CardStackView.Adapter<CardStackView.ViewHolder>,这个Adapter是作者在最基础的adapter上进行了一次封装，用起来比最基本的Adapter简单好用。

所以我们今天主要讲解通过继承StackAdapter<T>来实现效果。

#### 1-首先先创建一个ScoresCardStackAdapter继承自StackAdapter<Integer>

```java
public class ScoresCardStackAdapter extends StackAdapter<Integer>
```

在这里StackAdapter<T> 的泛型T，是根据你要传入数据的类型而定，此处Integer是为了，卡片显示不同颜色，传入颜色的值，而我的其他数据是使用后续自定义的updataData()方法去传递的，当然你也可以根据你自己的数据类型决定，在后续讲解中的bindView方法和updataData()你会明白此处。

#### 2-在依次实现一下两个个方法
因为StackAdapter<T>本身是个抽象类，其中只定义了如下的方法，但是并没有实现，所以在我们继承之后，要自己去实现这两个方法。

##### 第一个方法

```java
protected CardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scores_card_item,parent,false);  //通过LayoutInflater加载布局
        CardViewHolder holder = new CardViewHolder(view);
        System.out.println("onCreateView");
        return holder;
    }
```
这个方法是用来创建每一项的的View的，也就是每一个卡片的view，返回一个ViewHolder，这个ViewHolder中保存着这个布局中的View,这个ViewHolder也需要我们自定义，稍后我们将讲解自定义ViewHolder的。

该方法中的参数viewType是用来判断该项是什么类型的，可以根据不同的类型，使用LayoutInflater加载不同的布局，创建不同的ViewHolder，从而实现不同的卡片样式。

viewType这个参数是由方法int getItemViewType(int postion)方法得到的
```java

@Override
    public int getItemViewType(int position) {
        System.out.println("getItemViewType");
        return super.getItemViewType(position);
    }
    
```
传入的参数position代表是第几项，重写该方法，根据position判断类型。

##### 第二个方法

```java
public void bindView(Integer data, int position, CardStackView.ViewHolder holder) {
        if(holder instanceof CardViewHolder)
        {
            CardViewHolder cardHolder = (CardViewHolder)holder;
            cardHolder.onBind(data,position,lessonList);
        }
        System.out.println("bindView");
    }

```

这个方法是在我们创建完VIew之后调用的，得到View后进行一些操作，比如显示数据等操作。

**注意：该方法中的参数data的类型为T，因为一开始定义了泛型Integer,所以此处为Integer。**


#### 4-在ScoresCardStackAdapter中创建CardViewHolder类
在上面看到了在onCreateView时需要返回一个ViewHolder，将每一项的布局缓存起来，以供后面反复使用。

```java
public static class CardViewHolder extends CardStackView.ViewHolder
    {
        View root;
        FrameLayout cardTitle;          //布局头部
        RecyclerView scoreList;         //布局头部下方的RecyclerView
        TextView titleText;             //布局头部中的标题
        public CardViewHolder(View view)
        {
            /*
                在创建ViewHolder是 对控件进行绑定
            */
            super(view);
            root = view;
            cardTitle = (FrameLayout)view.findViewById(R.id.card_title);
            titleText = (TextView)view.findViewById(R.id.card_title_text);
            scoreList = (RecyclerView)view.findViewById(R.id.scores_list);
            System.out.println("CardViewHolder constructor");
        }

        public void onBind(Integer backgroundColorId,int position,List<List<LessonData>> dataList)
        {
            /*
                该方法是在bindView 调用时被调用的，因为可能有不同的布局，因而有不同的ViewHolder，将bindView实现的操作放在了ViewHolder中的onBind方法中，会使代码看来起更简洁，易懂。
            */
            cardTitle.getBackground().setColorFilter(ContextCompat.getColor(getContext(),backgroundColorId), PorterDuff.Mode.SRC_IN);//传入的int值，其实是一个颜色，在这里改变头部的颜色
            ScoresListAdapter adapter = new ScoresListAdapter(dataList.get(position));
            scoreList.setLayoutManager(new LinearLayoutManager(getContext()));
            scoreList.setAdapter(adapter);
            System.out.println("holder onBind");
        }

        @Override
        public void onItemExpand(boolean b) {
        
            /*
            该方法是在，其他Item被展开时，自动隐藏和显示的。
            */
            scoreList.setVisibility(b ? View.VISIBLE : View.GONE);
            System.out.println("holder onItemExpand");
        }
    }

```

#### 5-其他方法说明
```java
@Override
    public int getItemCount() {
        System.out.println("getItemCount");
        return super.getItemCount();
    }
```
该方法决定该列表可以显示多少项卡片，所以你需要根据你传入的数据去，去判断返回多少项。



### 5-在Activity中绑定CardStackView并为其绑定Adapter
做完了前面的准备工作，我们接下来要在Activity或者Fragment中使用它。

首先对其进行绑定，创建Adapter，并将它和Adapter绑定
```java

        mCardStack = (CardStackView)findViewById(R.id.cardStackView);
        ScoresCardStackAdapter adapter = new ScoresCardStackAdapter(this);
        mCardStack.setAdapter(adapter);
```

绑定Adapter之后并不能显示，我们还需要给Adapter更新数据，让其显示出来。

在这之前我在ScoresCardStackAdapter中重载了StackAdapter<T>中的 updateData(List<T> data)方法，因为不仅仅要传入颜色数组，还有我自己的数据
```java

//StackAdapter中的updateData方法
public void updateData(List<T> data) {
        this.setData(data);
        this.notifyDataSetChanged();
    }
    

//ScoresCardStackAdapter中重载了updateData方法
public void updateData(List data,List<List<LessonData>> lessonList) {
        this.lessonList = lessonList;
        updateData(data);
        System.out.println("Updata!");
    }

```
**注意：只有在你给Adapter中传入了数据，并且调用了Adapter的notifyDataSetChanged()，才能正常显示出画面+数据，不调用该方法，将会是一片空白，所以不管你是使用已经有的updataData()方法，还是自己定义方法传递数据，都必须确保Adapter的notifyDataSetChanged()方法被调用，才能正常显示**

在传入数据前，准备了一些测试数据
```java
//颜色数组
Integer[] color = {
            R.color.holo_blue_bright,
            R.color.holo_orange_light,
            R.color.holo_purple,
            R.color.holo_red_light
    };
//模拟数据
    String[] name = {"数据结构","计算机网络","编译原理","C语言","算法设计","FPGA编程"};
    String[] scores = {"77","87","65","98","74","80"};

    List<List<LessonData>> lists = new LinkedList<>();
        for(int i = 0;i<4;i++)
        {
            List<LessonData> list = new LinkedList<>();
            for(int j = 0;j<6;j++)
            {
                list.add(new LessonData(name[j],scores[j]));
            }
            lists.add(list);
        }
        
    //准备完数据之后在调用 updataData方法
    adapter.updateData(Arrays.asList(color),lists);
```
LessonData是我自己定义的数据类（测试用）
```java
public class LessonData {
    String lessonName;
    String lessonScore;

    public LessonData(String lessonName, String lessonScore) {
        this.lessonName = lessonName;
        this.lessonScore = lessonScore;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonScore() {
        return lessonScore;
    }

    public void setLessonScore(String lessonScore) {
        this.lessonScore = lessonScore;
    }
}

```


### 6-CardStackView动画效果
经过上面步骤，CardStackView就已经有了想要的效果，不过CardStackView原生提供了三种动画效果，分别是
- AllDown
- UpDown
- UpDownStack

使用方法非常简单分别是
```java
mCardStack.setAnimatorAdapter(new AllMoveDownAnimatorAdapter(mCardStack));
    
mCardStack.setAnimatorAdapter(new UpDownAnimatorAdapter(mCardStack));
        
mCardStack.setAnimatorAdapter(new UpDownStackAnimatorAdapter(mCardStack));
```

***

##### 经过上面的步骤就可以正常使用CardStackView了
##### 这个是原作者的github：https://github.com/loopeer/CardStackView#cardstackview
##### 这个是我自己的Demo完整代码：https://github.com/lzl471954654/MyCardStack
##### 有什么不足，还请多多包涵，谢谢阅读。
