# AGMobileGift

### GIFs
<img src="https://cloud.githubusercontent.com/assets/1777595/25045287/34a42230-2134-11e7-8d86-ff64100ad61a.gif" width="32%"> <img src="https://cloud.githubusercontent.com/assets/1777595/25045289/34a52400-2134-11e7-8488-3cbe18b63736.gif" width="32%"> <img src="https://cloud.githubusercontent.com/assets/1777595/25045288/34a42e7e-2134-11e7-98b6-a5d6e6754982.gif" width="32%">

### Gravity
<img src="https://cloud.githubusercontent.com/assets/1777595/26116081/8e029c0e-3a6a-11e7-9626-2753a9e31b83.gif" width="32%">

[Agilie Team](https://agilie.com/en/ios-development-services) would like to offer you our new lightweight open-source library called AGMobileGiftInterface. 
This library simplifies interaction with GIF images and can be easily integrated into your project.

When can you use AGMobileGift?
Use our library if you need to show a GIF image after performing a certain pre-specified action. AGMobileGiftInterface can also be helpful for creating animated greetings, designing splash screens or loading, upgrading and supplementing online games as well as in other similar cases.
Our library helps you achieve the desired result in an easy way with as little lines of code as possible.

## Example

### How does it work?

Gifs:
After an animated picture has been played, the controller managing it closes. And if you want to add a new image, just put it into the project and provide the GIF path as parameter and call method *show*. 

````kotlin
val giftInterfaceImpl = AGMobileGiftInterfaceImpl()
giftInterfaceImpl.show(this, R.drawable.lady_bug)

````

Gravity:

First, create an instance of *GravityControllerImpl*. Pass context instance and root *ViewGroup* as constructor parameters:

````kotlin
val gravityController = GravityControllerImpl(this, rootLayout)
````

To initiate gravity animation of all subviews (except ViewGroup instances) and then return back these UI elements simply call appropriate methods:

````kotlin
gravityController.start()
gravityController.stop()
````

### Our example of using AGMobileGift
We’ve used this library when working on Easter greeting program. Quite simple, it has 3 GIF images in its reserve (Ladybird, Rabbit, Fox) but can also be supplemented with new ones. 
We made example to congratulate the use on the day of Easter.

Influenced with [PhysicsLayout](https://github.com/Jawnnypoo/PhysicsLayout) project, we have recently supplemented our library with the new interesting animation. In its updated version, our library captures any interface (screen or view) and throws its UI elements over under the influence of gravity, so that one can move them from side to side obliquely. Call *stop()* method to return all the UI elements to their original location.

This animation can be easily used during the development of any application as an event activated after a specified user action.

## Usage

### Gradle

Add dependency in your `build.gradle` file:
````
compile 'com.agilie.agmobilegift:AGMobileGiftInterface:0.1.0'
````

### Maven
Add rependency in your `.pom` file:
````
<dependency>
  <groupId>com.agilie.agmobilegift</groupId>
  <artifactId>AGMobileGiftInterface</artifactId>
  <version>0.0.1</version>
  <type>pom</type>
</dependency>
````

## Requirements

AGMobileGift works on Android API 19+

## Author

This library is open-sourced by [Agilie Team](https://www.agilie.com)

## License

AGMobileGift is available under the [MIT](https://github.com/agilie/AGMobileGift/blob/dev/LICENSE.txt) license.
