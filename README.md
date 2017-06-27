# AGMobileGift

[ ![Download](https://api.bintray.com/packages/agilie/maven/AGMobileGiftInterface/images/download.svg) ](https://bintray.com/agilie/maven/AGMobileGiftInterface/_latestVersion)

Check the [fox](https://dribbble.com/shots/3459471-Animations-for-GitHub-open-source-libraries) and the [ladybug](https://dribbble.com/shots/3432735-Animations-for-GitHub-open-source-libraries) gifs on Dribbble!

<img src="https://cloud.githubusercontent.com/assets/1777595/25045287/34a42230-2134-11e7-8d86-ff64100ad61a.gif" width="32%"> <img src="https://cloud.githubusercontent.com/assets/1777595/25045289/34a52400-2134-11e7-8488-3cbe18b63736.gif" width="32%"> <img src="https://cloud.githubusercontent.com/assets/1777595/25045288/34a42e7e-2134-11e7-98b6-a5d6e6754982.gif" width="32%">

[Agilie Team](https://agilie.com/en/ios-development-services) would like to offer you our new lightweight open-source library called AGMobileGiftInterface. 
This library simplifies interaction with GIF images and can be easily integrated into your project.

When can you use AGMobileGift?
Use our library if you need to show a GIF image after performing a certain pre-specified action. AGMobileGiftInterface can also be helpful for creating animated greetings, designing splash screens or loading, upgrading and supplementing online games as well as in other similar cases.
Our library helps you achieve the desired result in an easy way with as little lines of code as possible.

## Example

### How does it work?

After an animated picture has been played, the controller managing it closes. And if you want to add a new image, just put it into the project and provide the GIF path as parameter and call method *show*. 

````kotlin
val giftInterfaceImpl = AGMobileGiftInterfaceImpl()
giftInterfaceImpl.show(this, R.drawable.lady_bug)

````

### Our example of using AGMobileGift
We’ve used this library when working on Easter greeting program. Quite simple, it has 3 GIF images in its reserve (Ladybird, Rabbit, Fox) but can also be supplemented with new ones. 
We made example to congratulate the use on the day of Easter.

## Usage

### Gradle

Add dependency in your `build.gradle` file:
````
compile 'com.agilie.agmobilegift:AGMobileGiftInterface:0.0.1'
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

## Troubleshooting

Problems? Check the [Issues](https://github.com/agilie/AGMobileGift/issues) block
to find the solution or create an new issue that we will fix asap.


## Author

This library is open-sourced by [Agilie Team](https://www.agilie.com) <info@agilie.com>

## Contributors

- [Eugene Surkov](https://github.com/ukevgen)

## Contact us
If you have any questions, suggestions or just need a help with web or mobile development, please email us at<br/> <android@agilie.com><br/>
You can ask us anything from basic to complex questions. <br/>
We will continue publishing new open-source projects. Stay with us, more updates will follow!<br/>

## License

The [MIT](LICENSE.md) License (MIT) Copyright © 2017 [Agilie Team](https://www.agilie.com)
