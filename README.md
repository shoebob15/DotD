![logo](https://github.com/shoebob15/DotD/assets/55066087/c52341f5-c11e-4af0-9992-cda9c8c42d14)

A top-down 2D dungeon crawler written in Java using the [LibGDX](https://github.com/libgdx/libgdx) framework - final project for my AP Computer Science A class

**This project has been retired due to lack of time and desire to program it**


## Code structure
Depths of the Dungeon (DotD) utilizes an entity-component system structure for managing entities. Entities contain components that can be utilized by certain systems. My ECS is very loose and unorganized, and should not be used as any example for any ECS in any scenario.

## Building
Open up the project in your favorite modern Java IDE, and build the project with Gradle

## Running
Go to the Releases tab and download the most recent jar file

## What I learned & things I would do differently
* Not use a pre-existing framework
  * I didn't particularly love LibGDX's API, which is mostly a personal thing.
* Format the codebase to be more uniform and consistent
  * Throughout development, I had to refactor the code multiple times due to oversights on my part. This led to an overall confusing code structure and many inconsistencies in the code
* Create reusable assets
  * This would've been much easier using my own game engine, since LibGDX doesn't provide any low-level APIs for pixel manipulation
* Keep code better organized with Git
  * Creating multiple branches would've been helpful, since large refactors often interfere with other ongoing developments
* Find better assets
* Better unified art style
* Less object orientation
  * If I had utilized it correctly, it would have helped (but I didn't)
* Write code that works always, not just once
  * I got lazy at the end and started arbitrarily putting in numbers that "just worked"
* Manage time better
* Focus on gameplay, not graphics    

## Credits
* Mr. Seaver, my amazing CSA teacher
* LibGDX Discord server, for weird niche issues I was having
