import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The List class stores the list of items. Items are stored in
 * an ArrayList of Strings. Supports adding of new items to the list
 * and printing of the entire current list.
 */
public class List {
    private ArrayList<Task> list;

    /**
     * Constructor for List class.
     */
    public List() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds a ToDo to the list.
     *
     * @param description The description of the task to be added
     */
    public void addTask(String description) {
        Task task = new ToDo(description);
        this.list.add(task);
        System.out.println("Task added successfully: \n" + task);
        System.out.println("Number of tasks in list: " + list.size());
    }

    /**
     * Adds an Event or Deadline to the list.
     *
     * @param description
     * @param date
     * @param type
     */
    public void addTask(String description, LocalDate date, String type) {
        Task task;
        if (type.equals("deadline")) {
            task = new Deadline(description, date);
        } else {
            task = new Event(description, date);
        }
        this.list.add(task);
        System.out.println("Task added successfully: \n" + task);
        System.out.println("Number of tasks in list: " + list.size());
    }

    /**
     * Changes the status for the task indicated by the user.
     *
     * @param taskNumber The index(plus 1) of the task to be marked as done
     * @return The string representation of the task after it is marked as done
     */
    public String changeTaskStatus(int taskNumber) throws DukeException{
        if (taskNumber <= 0 || taskNumber > this.list.size()) {
            throw new DukeException("Task does not exist. Use list to check all tasks available.");
        } else {
            Task task = list.get(taskNumber - 1);
            task.doneTask();
            return task.toString() + "\nNumber of tasks remaining: " + list.size();
        }
    }

    public String deleteTask(int taskNumber) throws DukeException{
        if (taskNumber <= 0 || taskNumber > this.list.size()) {
            throw new DukeException("Task does not exist. Use list to check all tasks available.");
        } else {
            Task task = list.get(taskNumber - 1);
            list.remove(taskNumber - 1);
            return task.toString() + "\nNumber of tasks remaining: " + list.size();
        }
    }

    /**
     * List the items in the list in the order added, along with a counter.
     */
    public void listItems() {
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i-1));
        }
    }
}
