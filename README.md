## ProjectYouVideo (Java OOP)

A small command-line (CLI) Java project built for an Object-Oriented Programming course.  
The system models a simplified media platform where users can manage:

- **Videos**
  - Publishable videos
  - Premium videos (support **subtitles**)
- **Podcasts**
  - Podcasts can contain **episodes**
- **Shows**
  - A show is created using an existing publishable video (uses the video title)

The application focuses on OOP design and domain modeling (Videos / Podcasts / Shows), while the `Main` class handles input/output and command parsing.

---

## Main Features

### Videos
- Create a publishable video
- Create a premium video (with its first subtitle)
- Add subtitles to premium videos
- Display video information by ID
- List subtitles of a premium video
- Remove a video (with integrity checks, e.g., cannot remove if used as an episode or show)

### Podcasts
- Create a podcast (initially with no episodes)
- Add an episode to a podcast
- Show podcast information by title
- List podcast episodes
- List podcasts by author
- Remove a podcast

### Shows
- Create a show from an existing publishable video
- Show show information by title
- Remove a show

---

## Project Structure (high-level)

- `src/Main.java`
  - CLI interface: reads commands, validates inputs, prints formatted output.
- `src/youVideo/YouVideoAppClass.java`
  - Core application/service layer that stores and manages domain objects:
    - videos
    - podcasts
    - shows
- `src/youVideo/*`
  - Domain entities and interfaces (e.g., `Podcast`, `Episode`, `Show`, `Video`, `Subtitle`, etc.)
- `src/dataStructures/*`
  - Custom data structures used by the project (e.g., `Array`, `Iterator`, ...)
