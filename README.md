### 1.Tips:
* 重命名：Refactor-Rename   
* 代码格式：Format Code/Optimize Code   
* [markdown语法](https://www.cnblogs.com/liugang-vip/p/6337580.html)
* [颜色RGB对照值](http://tool.oschina.net/commons?type=3)
* [开闭原则-对修改关闭，对拓展开放](https://www.cnblogs.com/kuibuqianli/p/9324500.html)：在面向对象的设计中，所有的逻辑都是从原子逻辑组合而来的， 而不是在一个类中独立实现一个业务逻辑。只有这样代码才可以复用，粒度越小，被复用的可能性就越大
### 2.java
* 接口

在JAVA编程语言中是一个抽象类型，是抽象方法的集合，接口通常以interface来声明。一个类通过继承接口的方式，从而来继承接口的抽象方法。类描述对象的属性和方法。接口则包含类要实现的方法。  

接口与类的区别  
1.接口不能用于实例化对象。  
2.接口没有构造方法。  
3.接口中所有的方法必须是抽象方法。  
4.接口不能包含成员变量，除了 static 和 final 变量。  
5.接口不是被类继承了，而是要被类实现。   
6.接口支持多继承。  

接口定义：
```
interface Animal {
   public void eat();
   public void travel();
}
```
接口实现：
```
public class MammalInt implements Animal{
 
   public void eat(){
      System.out.println("Mammal eats");
   }
 
   public void travel(){
      System.out.println("Mammal travels");
   } 
 
   public int noOfLegs(){
      return 0;
   }
 
   public static void main(String args[]){
      MammalInt m = new MammalInt();
      m.eat();
      m.travel();
   }
}
```
* 匿名内部类定义：  
假如一个局部内部类只被用一次（只用它构建一个对象），就可以不用对其命名了，这种没有名字的类被称为匿名内部类（anonymous inner class），其代码格式通常为：
``` 
new SuperType(construction parameters){
    inner class methods and data
}; 
```
* Fresco与Glide区别：

1.Fresco封装了圆角使用
```

public void setRoundImageSrc(SimpleDraweeView draweeView, String src, float radius){
    RoundingParams roundingParams = RoundingParams.fromCornersRadius(radius);
    draweeView.setHierarchy(
            new GenericDraweeHierarchyBuilder(draweeView.getResources())
            .setRoundingParams(roundingParams)
            .build());
    draweeView.setImageURI(Uri.parse(src));
｝
```
Glide需要自定义
```
public static class GlideRoundTransform extends BitmapTransformation {
 
    private static float radius = 0f;
 
    public GlideRoundTransform(Context context) {
        this(context, 4);
    }
 
    public GlideRoundTransform(Context context, int dp) {
        super(context);
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
    }
 
    @Override 
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool, toTransform);
    }
 
    private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;
 
        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }
 
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
        return result;
    }
 
    @Override public String getId() {
        return getClass().getName() + Math.round(radius);
    }
}
 
//使用
Glide.with(context).load(imageUrl).transform(new GlideRoundTransform(context)).into(imageView)
//注意：使用了transform以后，就不能使用centercrop，fitcenter等方法
```
2.缓存方式不同

**Fresco**

三级缓存：Bitmap缓存，未解码图片缓存， 文件缓存。

Bitmap缓存使app运行更流畅，磁盘缓存还可以通过代码来设置不同手机的缓存容量。
***但只缓存原始图像。***
```
public void initFresco(Context context, String diskCacheUniqueName){
    DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(context)
            .setMaxCacheSize(DISK_CACHE_SIZE_HIGH)
            .setMaxCacheSizeOnLowDiskSpace(DISK_CACHE_SIZE_LOW)
            .setMaxCacheSizeOnVeryLowDiskSpace(DISK_CACHE_SIZE_VERY_LOW)
            .build();
 
    ImagePipelineConfig config = ImagePipelineConfig.newBuilder(context)
            .setMainDiskCacheConfig(diskCacheConfig)
            .build();
    Fresco.initialize(context, config);
}
```

**Glide**

两种缓存：内存、磁盘缓存。

***会根据ImageView控件尺寸获得对应的大小的bitmap来展示***，从而缓存也可以针对不同的对象：原始图像（source），结果图像(result); 可以通过.diskCacheStrategy()方法设置：
```
public enum DiskCacheStrategy {    
  /** Caches with both {@link #SOURCE} and {@link #RESULT}. */    
  ALL(true, true),    
  /** Saves no data to cache. */    
  NONE(false, false),    
  /** Saves just the original data to cache. */    
  SOURCE(true, false),    
  /** Saves the media item after all transformations to cache. */    
  RESULT(false, true);
}
```

3.bitmap操作Glide更简单

**Fresco**引入了关闭的引用，只能在作用域范围内使用，当持有者离开该作用域时要关闭该饮用。

**Glide**可直接获得bitmap对象：
```

Bitmap myBitmap = Glide.with(applicationContext)  
    .load(yourUrl)  
    .asBitmap() //必须  
    .centerCrop()  
    .into(500, 500)  
    .get() 
```

4.Fresco增加功能
* SimpleDraweeView控件可以指定图片的宽高比例（app:viewAspectRatio），对于手机适配非常重要;
* 图片加载进度
* 先加载小尺寸图片，再加载大尺寸的（Glide只有占位图）



