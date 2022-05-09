# school-registration-system

### Run

``docker-compose up``

### Postman Collection

Find file in folder `src/test/resources/postman/School Registration System.postman_collection.json`

### Initial set of data

```
INSERT INTO `courses` (`id`, `teacher`, `title`, `uuid`)
VALUES
(9, 'Orgo Wells', 'Philosophy 101', '7a98ab8c-62b3-46cb-96c0-6460a089643a'),
(11, 'Fatima Lopez', 'Maths 202', '3f70d725-c05e-4ca7-9bfa-cfc56033573a'),
(12, 'Ruben Wilson', 'Biology 102', 'f642c532-3b86-4c48-8da6-f9b7bb31f039'),
(13, 'Fatima Lopez', 'Technology 313', '8f0b58b6-6163-461c-8394-df52c9808977'),
(14, 'John Mills', 'IT 101', '8a99b80b-bc96-41fa-bd59-d57bb899280c'),
(15, 'Harg Tholan', 'Finances 302', 'ac07f18c-66b3-4ebc-a2d7-ef22d7de64f2'),
(16, 'Ruben Wilson', 'History 212', '9e58a353-69d0-4d8a-bfa4-07a2c45086e4');

INSERT INTO `students` (`id`, `age`, `name`, `uuid`)
VALUES
(1, 12, 'Serena Pena', 'f9764ef8-5f4c-452c-ab65-674611183d7b'),
(2, 14, 'Lupe Gutierrez', '53ec75e9-e4e6-48d7-a0f7-71c8018b56ca'),
(3, 14, 'Skylar Longvie', 'aaa271ea-a25d-4f4e-bcc3-12c5fe975bda'),
(4, 11, 'Paul Rudd', 'a3559f63-f31c-43b1-81a5-15e10a0bed21'),
(5, 16, 'George Unwin', '7ed62b81-c9b9-4d7d-af75-3d2dd63f5185'),
(6, 15, 'Tarcia Gomel', '9afc3520-3a64-41db-83d1-1277a5819e22'),
(7, 16, 'Will Falcon', 'd27c313a-a3f9-4906-bb43-47e8fab8423b'),
(8, 15, 'Bo Li', '0a3e6dff-b42a-4922-9a55-28ecefaeb625');


INSERT INTO `students_courses` (`student_id`, `course_id`)
VALUES
(1, 9),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(4, 12),
(4, 15),
(5, 12);
```