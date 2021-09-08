package duke;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The TaskList class stores the list of items. Items are stored in
 * an ArrayList of Strings. Supports adding of new items to the list
 * and printing of the entire current list.
 */
public class TaskList {
    /** The list of tasks stored */
    private ArrayList<Task> list;
    private Ui ui;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
        this.ui = new Ui();
    }

    /**
     * Constructor for TaskList.
     * Constructs the list based on the given array of strings.
     *
     * @param taskList An array of strings of tasks.
     */
    public TaskList(ArrayList<String> taskList) {
        this();

        for (String s : taskList) {
            String[] splitData = s.split(",");
            switch(splitData[0]) {
            case("T"):
                addToDo(splitData[2], Boolean.parseBoolean(splitData[1]));
                break;
            case("D"):
                addDeadline(splitData[2], LocalDate.parse(splitData[3]), Boolean.parseBoolean(splitData[1]));
                break;
            case("E"):
                addEvent(splitData[2], LocalDate.parse(splitData[3]), Boolean.parseBoolean(splitData[1]));
                break;
            default:
                break;
            }
        }
    }

    /**
     * Returns the array of tasks.
     *
     * @return Array of tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Adds a ToDo task to the list and saves to the file.
     *
     * @param description The description of the task.
     * @return The task added.
     */
    public Task addToDo(String description) {
        Task task = new ToDo(description);
        this.list.add(task);
        return task;
    }

    /**
     * Adds a ToDo task to the list without saving to file.
     *
     * @param description The description of the task.
     * @param isDone Indicates if the task is done.
     */
    public void addToDo(String description, boolean isDone) {
        Task task = new ToDo(description, isDone);
        this.list.add(task);
    }

    /**
     * Adds a Deadline task to the list and saves to the file.
     *
     * @param description The description of the task.
     * @param date The deadline of the task.
     * @return The Deadline task added.
     */
    public Task addDeadline(String description, LocalDate date) {
        Task task = new Deadline(description, date);
        this.list.add(task);
        return task;
    }

    /**
     * Adds a Deadline task to the list without saving to the file.
     *
     * @param description The description of the task.
     * @param date The deadline of the task.
     * @param isDone Indicates if the task is done.
     */
    public void addDeadline(String description, LocalDate date, boolean isDone) {
        Task task = new Deadline(description, date, isDone);
        this.list.add(task);
    }

    /**
     * Adds an Event task to the list and saves to the file.
     *
     * @param description The description of the task.
     * @param date The date of the event.
     * @return The Event task added.
     */
    public Task addEvent(String description, LocalDate date) {
        Task task = new Event(description, date);
        this.list.add(task);
        return task;
    }

    /**
     * Adds an Event task to the list without saving to the file.
     *
     * @param description The description of the task.
     * @param date The date of the event.
     * @param isDone Indicates if the task is done.
     */
    public void addEvent(String description, LocalDate date, boolean isDone) {
        Task task = new Event(description, date, isDone);
        this.list.add(task);
    }

    /**
     * Changes the status for the task indicated by the user.
     *
     * @param taskNumber The index(plus 1) of the task to be marked as done.
     * @return The string representation of the task after it is marked as done.
     */
    public String markAsDone(int taskNumber) throws DukeException {
        if (taskNumber <= 0 || taskNumber > this.list.size()) {
            throw new DukeException("Task does not exist. Use list to check all tasks available.");
        }

        Task task = list.get(taskNumber - 1);
        task.setDone(true);
        return task.toString() + "\nNumber of tasks remaining: " + list.size();
    }

    /**
     * Deletes the task indicated from the list.
     *
     * @param taskNumber The index of the task to be deleted.
     * @return String representation of the task deleted.
     * @throws DukeException If taskNumber is an invalid index.
     */
    public String deleteTask(int taskNumber) throws DukeException {
        if (taskNumber <= 0 || taskNumber > this.list.size()) {
            throw new DukeException("Task does not exist. Use list to check all tasks available.");
        }

        Task task = list.get(taskNumber - 1);
        list.remove(taskNumber - 1);
        return task.toString() + "\nNumber of tasks remaining: " + list.size();
    }

    /**
     * Takes in a keyword and searches for all tasks that contains the keyword in
     * the description. Returns the results in an ArrayList of Strings.
     *
     * @param keyword The keyword to search for.
     * @return An Arraylist of Strings containing all matching tasks.
     */
    public ArrayList<String> findTask(String keyword) {
        ArrayList<String> resultArray = new ArrayList<>();
        keyword = keyword.toLowerCase();

        for (Task t : list) {
            if (t.getDescription().toLowerCase().contains(keyword)) {
                resultArray.add(t.toString());
            }
        }

        return resultArray;
    }

    /**
     * Returns an array of tasks in their string representation.
     *
     * @return Array of tasks in string form.
     */
    public ArrayList<String> getTaskList() {
        ArrayList<String> tasks = new ArrayList<>();

        for (Task t : list) {
            tasks.add(t.toString());
        }

        return tasks;
    }
}
