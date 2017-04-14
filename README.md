# AGMobileGift

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

TBD

## Requirements

AGMobileGift works on Android API 19+

## Installation

TBD

## Author

This library is open-sourced by [Agilie Team](https://www.agilie.com)

## License

AGMobileGift is available under the [MIT](https://github.com/agilie/AGMobileGift/blob/dev/LICENSE.txt) license.
