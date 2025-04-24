 INSERT INTO trainee (trainee_id, first_name, last_name, email, department, role)
VALUES
  (1, 'Alice', 'Johnson', 'alice.johnson@example.com', 'HR', 'Analyst'),
  (2, 'Bob', 'Smith', 'bob.smith@example.com', 'IT', 'Developer'),
  (3, 'Charlie', 'Brown', 'charlie.brown@example.com', 'Marketing', 'Manager'),
  (4, 'David', 'Lee', 'david.lee@example.com', 'Finance', 'Accountant'),
  (5, 'Emily', 'Davis', 'emily.davis@example.com', 'Sales', 'Representative');
  INSERT INTO trainer (trainer_id, first_name, last_name, email, expertise)
VALUES
  (101, 'Alex', 'Turner', 'alex.turner@example.com', 'Data Science'),
  (102, 'Bella', 'White', 'bella.white@example.com', 'Cloud Computing'),
  (103, 'Chris', 'Green', 'chris.green@example.com', 'Cybersecurity'),
  (104, 'Diana', 'Black', 'diana.black@example.com', 'Project Management'),
  (105, 'Ethan', 'Grey', 'ethan.grey@example.com', 'Software Engineering');
  INSERT INTO course (course_id, course_name, description, duration)
VALUES
  (201, 'Data Science 101', 'Introduction to data science concepts', '2023-11-23 12:00:00'),
  (202, 'Cloud Fundamentals', 'Basics of cloud computing', '2023-11-24 10:00:00'),
  (203, 'Cyber Security Essentials', 'Essential cybersecurity practices', '2023-11-25 14:00:00'),
  (204, 'Project Management Basics', 'Project management methodologies', '2023-11-26 09:00:00'),
  (205, 'Software Development 101', 'Introduction to software development', '2023-11-27 13:00:00');
  INSERT INTO training_Session (session_id, course_id, trainer_id, start_date, end_date, venue)
VALUES
  (301, 201, 101, '2023-12-01', '2023-12-05', 'Online'),
  (302, 202, 102, '2023-12-08', '2023-12-12', 'In-person'),
  (303, 203, 103, '2023-12-15', '2023-12-19', 'Hybrid'),
  (304, 204, 104, '2023-12-22', '2023-12-26', 'Online'),
  (305, 205, 105, '2023-12-29', '2024-01-02', 'In-person');
  INSERT INTO enrollment (enrollment_id, trainee_id, session_id)
VALUES
  (401, 1, 301),
  (402, 2, 302),
  (403, 3, 303),
  (404, 4, 304),
  (405, 5, 305);
