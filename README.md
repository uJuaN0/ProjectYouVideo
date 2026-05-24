# ProjectYouVideo (Java OOP)

A command-line Java project built for an Object-Oriented Programming course.  
The system models a simplified media streaming platform where users can manage videos, podcasts, shows, authors, and tagged content.

---

## Main Features

### Videos

- Create a publishable video
- Create a premium video with subtitle support
- Add subtitles to premium videos
- Display video information by ID
- List subtitles of a premium video
- Remove a video, with integrity checks:
  - A video cannot be removed if it is being used in a show
  - A video cannot be removed if it is being used in a podcast episode

### Podcasts

- Create a podcast, initially with no episodes
- Add episodes to a podcast, with date validation
- Show podcast information by title, including tags
- List podcast episodes in reverse chronological order
- List podcasts by author
- Remove a podcast and all of its episodes

### Shows

- Create a show from an existing publishable video
- Show show information by title, including tags
- List shows by author, ordered by date and then by title
- Remove a show without removing the underlying video

### Tags

- Add a tag to a podcast or show
- Remove a tag from a podcast or show
- List all content tagged with a given tag
- Filter tagged content by type:
  - `SHOW`
  - `PODCAST`
  - `ALL`
- Order tagged content by title:
  - `ASC`
  - `DES`

### Authors

- List all authors by productivity
- Productivity is based on the number of shows and podcasts created by each author

---

## Project Structure
src/
├── Main.java
└── youVideo/
    ├── YouVideoApp.java
    ├── YouVideoAppClass.java
    ├── Video.java
    ├── PublishableVideo.java
    ├── PremiumVideo.java
    ├── PublishableVideoClass.java
    ├── PremiumVideoClass.java
    ├── Subtitle.java
    ├── Episode.java
    ├── EpisodeClass.java
    ├── Podcast.java
    ├── PodcastClass.java
    ├── Show.java
    ├── ShowClass.java
    ├── Author.java
    ├── AuthorClass.java
    ├── Taggable.java
    ├── TaggableClass.java
    ├── TaggableComparator.java
    └── TaggableDescComparator.java

OOP Concepts Applied
Interfaces and abstract classes — used to separate contracts from implementations
Inheritance and polymorphism — used for shared behaviour between related entities
Encapsulation — instance variables are private and accessed through methods
Generics — used in collections and comparators
Java Collections Framework — used through structures such as HashMap, LinkedList, TreeSet, and SortedSet
Exceptions — used to handle error cases clearly
Comparators — used to define custom ordering for shows, authors, tags, and tagged content
Authors
Juan Lima 75513
Miguel Passão 75460
