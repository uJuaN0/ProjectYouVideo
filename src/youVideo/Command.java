package youVideo;

public enum Command {
    ADD_PUBLISHABLE("createpublishable", "creates a new publishable video"),
    ADD_PREMIUM("createpremium", "creates a new publishable Premium video"),
    ADD_SUBTITLE("addsubtitle", "adds subtitle to Premium video"),
    GET_VIDEO("getvideo", "presents publishable video data from its id"),
    GET_SUBTITLES("subtitles", "lists Premium video subtitles"),
    CREATE_PODCAST("createpodcast", "creates a new podcast with no episodes"),
    ADD_EPISODE("addepisode", "adds an episode to a podcast"),
    GET_PODCAST("getpodcast", "presents podcast data from its title"),
    EPISODES("episodes", "lists podcast episodes"),
    AUTHOR_PODCASTS("authorpodcasts", "lists all podcasts of an author"),
    REMOVE_PODCAST("removepodcast", "removes a podcast"),
    CREATE_SHOW("createshow", "creates show using an existing publishable video"),
    GET_SHOW("getshow", "presents show data from its title"),
    AUTHOR_SHOWS("authorshows", "lists all shows of an author"),
    REMOVE_SHOW("removeshow", "removes a show"),
    REMOVE_VIDEO("removevideo", "removes a publishable video"),
    AUTHORS_PRODUCTIVITY("authorsproductivity", "lists authors by their productivity"),
    ADD_TAG("addtag", "adds a tag to a show or podcast"),
    REMOVE_TAG("removetag", "removes a tag from a show or podcast"),
    TAGGED("tagged", "lists content tagged with a given tag"),
    HELP("help", "shows the available commands"),
    EXIT("exit", "terminates the execution of the program");

    private final String command;
    private final String description;

    Command(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }
}
