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
