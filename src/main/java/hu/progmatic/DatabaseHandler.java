package hu.progmatic;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

public class DatabaseHandler {
    final protected Sql2o adatbazis = new Sql2o(
            "jdbc:mysql://localhost:3306/progmatic",
            "progmatic",
            "progmatic"
    );

    public DatabaseHandler() {
        try (Connection connection = adatbazis.open()) {
            connection.createQuery(
                    """
                            drop table if exists kalandjatek_room, kalandjatek_door, kalandjatek_item, kalandjatek_inventory
                            """
            ).executeUpdate();
            connection.createQuery(
                """
                        create table kalandjatek_inventory (
                          id int auto_increment primary key
                        )
                        """
            ).executeUpdate();
            connection.createQuery(
                """
                        insert into kalandjatek_inventory () 
                        values (),(),(),(),(),(),(),(),(),(),(),(),(),(),(),(),(),(),(),(),(),(),(),(),(),(),(),(),(),(),(),(),()
                        """
            ).executeUpdate();
            connection.createQuery(
                    """
                            create table kalandjatek_room (
                              id int auto_increment primary key,
                              name text,
                              inventory_id int not null,
                              foreign key (inventory_id) references kalandjatek_inventory(id)
                            )
                            """
            ).executeUpdate();
            connection.createQuery(
                    """
                            create table kalandjatek_door (
                              id int auto_increment primary key,
                              room1Id int not null,
                              room2Id int not null,
                              foreign key (room1Id) references kalandjatek_room(id),
                              foreign key (room2Id) references kalandjatek_room(id)
                            )
                            """
            ).executeUpdate();
            connection.createQuery(
                    """
                            create table kalandjatek_item (
                              id int auto_increment primary key,
                              name text not null,
                              roomId int not null,
                              foreign key (roomId) references kalandjatek_room(id)
                            )
                            """
            ).executeUpdate();
            connection.createQuery(
                    """
                            insert into kalandjatek_room (name, inventory_id) 
                            values
                             ('Bolt',1),
                             ('Főtér',2),
                             ('Kocsma',3),
                             ('Templom',4),
                             ('Pince',5)
                            """
            ).executeUpdate();
        }
    }

    public int getRoomCount() {
        try (Connection connection = adatbazis.open()) {
            return connection.createQuery(
                    """
                            select count(id) from kalandjatek_room;
                            """
            ).executeScalar(Integer.class);
        }
    }

    public List<String> getRoomsNextDoor(String roomName) {
        try (Connection connection = adatbazis.open()) {
            Query query = connection.createQuery(
                    """
                            select
                                roomNextDoor.name
                            from kalandjatek_room as room
                            join kalandjatek_door as door
                                on room.id = door.room1Id or room.id = door.room2Id
                            join kalandjatek_room as roomNextDoor
                                on door.room1Id = roomNextDoor.id or door.room2Id = roomNextDoor.id
                            where
                                room.name = :roomName
                                and room.id != roomNextDoor.id
                            """
            );
            query.addParameter("roomName", roomName);
            return query.executeScalarList(String.class);
        }
    }

    public void addDoor(String room1, String room2) {
        try (Connection connection = adatbazis.open()) {
            Integer roomId1 = getRoomId(room1);
            Integer roomId2 = getRoomId(room2);
            connection.createQuery(
                            """
                                    insert into kalandjatek_door
                                    (room1Id, room2Id)
                                    VALUES
                                    (:roomId1, :roomId2)
                                    """
                    )
                    .addParameter("roomId1", roomId1)
                    .addParameter("roomId2", roomId2)
                    .executeUpdate();
        }
    }

    private Integer getRoomId(String roomName) {
        try (Connection connection = adatbazis.open()) {
            return connection.createQuery(
                            """
                                    select id from kalandjatek_room where name = :roomName
                                    """
                    )
                    .addParameter("roomName", roomName)
                    .executeScalar(Integer.class);
        }
    }

    public void createItem(String itemName, String roomName) {

    }

    public List<String> getItems(String roomName) {
        return null;
    }
}

