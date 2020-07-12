**IMPORTANT**
- Please go through all the classes. I think ive done a reasonably in-depth implementation of all the classes and methods. I think it should be very clear for you now to implement the player class.
- I've also completely implemented the Goal composite pattern. No ambiguity there either its concrete and complete
- I have added another method to the Interactable interface called `defeatedObject()` which just tells us if the object has been picked up/destroyed. I think you should call this right after calling `playerIntersects()`, because if `defeatedObject()` returns `true`, then you know to check `isCompleted()` from the Goal interface.
- Alternatively, if you like you can go ahead and have `defeatedObject()` be in a separate interface, since some objects like walls? can't be defeated. I've kept it in the same interface, just incase in the future we decide to somehow incoporate any of the interactables into our goals

**Interactables**
- `interactable.playerIntersect()`
    - returns `-1` if from Boulder class
        - So you need to use this info to do an additional check accross the correspondng square on the hashmap
    - returns `0` for standard destructable object, e.g. potion, treasure.
        - this means you allow the player to move through to the square
    - returns `1` if object direclty does not allow pass through i.e. wall

**Door**
- read `playerIntersect()` implementation to to see how door opens. Make sure to code player class to allow this.


**TODO**

- Fully implement player class
- this means all the methods, creating the hashmap(x,y) data structure that outputs an interactable object
- establishing the conditional structure for player movement (up, down, left right)
    - calling the `playerIntersect()` method after getting the interactable object from the hashmap
    - making the appropriate deicision for movement based on the return value. Refer to the subsection above for the return values
    - calling the `isCompleted()` method, and subsequently telling Dungeon class whether the goal has been completed.
    - plus anything I missed relevant to player class ofc.