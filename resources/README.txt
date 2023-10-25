AppIconsGenerator v 1.0

March 2015

Author:
Fadi Hatem

The AppIconsGenerator is intended to faciliate the generation of over 50 images required for the android and IOS apps 
based on an image of large dimensions since most image sizes will be of smaller dimensions and the resize will not be 
affected if the original base image is of larger dimensions.

Simply create a directory where you put your app icon base PNG image.
This java program is compatible with Java 1.6 or later and all you need to run it once compiled is to pass the path of the
directory as a first argument and the name of your png base image file without the file extensions as a second argument:
Example:

java AppIconsGenerator YourPathWhereTheResourcesFilesAndImageAre YourBaseImageFileNameWIthout.PNG [android|iosScreenshots|iosLaunchImages|windows]

If optional argument for android etc. is not supplied it will do all optiona

Two directories will be created in the specified path Android and IOS.

1-The Android folder:

Under the Android directory 4 directories will be created:
drawable-hdpi
drawable-mdpi
drawable-xhdpi
drawable-xxhdpi

With an appropriate size image in each of these directories

Also 3 files will be created in the Android directory 
that can be used for the google play store listing.

2-The IOS folder:

Two folders will be created AppIcon.appiconset and LaunchImage.launchimage with appropriate
images in each folder will be placed.  Also 3 images will be placed in the IOS folder that
can be used for the appstore listing.

3-Windows folder:

Windows related files will be created

Enjoy
