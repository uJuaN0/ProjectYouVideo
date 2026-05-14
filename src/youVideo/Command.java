package youVideo;

public enum Command {
    CREATEPUBLISHABLE("creates a new publishable video"),
    CREATEPREMIUM("creates a new publishable Premium video"),
    ADDSUBTITLE("adds subtitle to Premium video"),
    GETVIDEO("presents publishable video data from its id"),
    SUBTITLES("lists Premium video subtitles"),
    CREATEPODCAST("creates a new podcast with no episodes"),
    ADDEPISODE("adds an episode to a podcast"),
    GETPODCAST("presents podcast data from its title"),
    EPISODES("lists podcast episodes"),
    AUTHORPODCASTS("lists all podcasts of an author"),
    REMOVEPODCAST("removes a podcast"),
    CREATESHOW("creates show using an existing publishable video"),
    GETSHOW("presents show data from its title"),
    AUTHORSHOWS("lists all shows of an author"),
    REMOVESHOW("removes a show"),
    REMOVEVIDEO("removes a publishable video"),
    AUTHORSPRODUCTIVITY("lists authors by their productivity"),
    ADDTAG("adds a tag to a show or podcast"),
    REMOVETAG("removes a tag from a show or podcast"),
    TAGGED("lists content tagged with a given tag"),
    HELP("shows the available commands"),
    EXIT("terminates the execution of the program"),
    UNKNOWN("");

    private final String description;

    Command(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}