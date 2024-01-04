# mapEditor

A simple yet interesting project in Java coded by me while @ Code for All_

## Description

mapEditor allows the user(s) to paint a grid with a cursor to their liking. The cursor and grid have a default size and color set by me but can easily be changed!

## Keybinds 

- **h:** Move cursor left
- **j:** Move cursor down
- **k:** Move cursor up
- **l:** Move cursor right
- **i:** Paint
- **r:** Cycle colors
- **u:** Clean all 

## Usage

1. Clone the repository.
2. Compile the Java files.
3. Run the mapEditor.

**OR**

1. Clone the repository.
2. Open the project directly from your preferred editor.
3. Have fun changing the code to your liking. (e.g. cursor size, paint color, keybinds etc.)
4. Run the project after you are done.

## Snippet of code

```java
    private void createCursor() {
        cursorRectangle = new Rectangle(PADDING, PADDING, customWidth, customHeight);
        cursorRectangle.setColor(CURSOR_COLOR);
        cursorRectangle.draw();
        cursorRectangle.fill();
        }
```
