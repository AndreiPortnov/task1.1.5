package jm.task.core.jdbc.constants;

public class StaticConstants {

    /**
     * Константы для доступа к БД
     */
    public static final String URL_KEY = "jdbc:mysql://localhost:3306/task_1_1_4";
     public static final String USERNAME_KEY = "root";
     public static final String PASSWORD_KEY = "root";


    /**
     * Наименования столбцов в таблице
     */
    public static final String TABLE_COLUMN_ID = "id";
    public static final String TABLE_COLUMN_NAME = "name";
    public static final String TABLE_COLUMN_LASTNAME = "last_name";
    public static final String TABLE_COLUMN_AGE = "age";

    /**
     * Исключения
     */
    public static final String USER_ADDING_ERROR = "не удалось добавить пользователя";
    public static final String GETTING_USERS_ERROR = "не удалось создать список";

}
