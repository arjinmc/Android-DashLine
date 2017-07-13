# Android-DashLine
a custom view for dash line with several colors

![img](https://github.com/arjinmc/Android-DashLine/blob/master/images/device-2017-07-13-210133.png) 

It's easy to use.

in xml

```xml
<com.arjinmc.dashcolorline.DashLine
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:dashGap="6dp"
        app:dashWidth="50dp"
        app:orientation="horizontal"
        app:thickness="10dp" />
```

You can change your colors by edite DashLine.java

```java
private int[] mColors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
```

or in Activity/Fragment/View
```java
 ((DashLine) findViewById(R.id.dashline_h))
                .setColors(Color.GRAY, Color.parseColor("#23d290"));
```

You also can use setXXXX to change these attributes as above in the xml file.
