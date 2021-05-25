INSERT INTO roles(role_id,role_name)  VALUES (1,'USER');
INSERT INTO roles(role_id,role_name)  VALUES (2,'ADMIN');
INSERT INTO roles(role_id,role_name)  VALUES (3,'SUBCLUBADMIN');

INSERT INTO users(id,uname,pwd,firstname,surname,email) VALUES (1,'admin','$2a$10$VF9rrlyhAIQqTsgEF6u0FuhuTB.9ZFWMDD4pFS8gEEfbWIXYmsdZK','Firat','BILGE','firat.bilge@yahoo.com');
INSERT INTO users(id,uname,pwd,firstname,surname,email) VALUES (2,'user','$2a$10$6RnGeibWXl1pI9ze57hXju9FirRNwTwVwlkqi2jfOJ/PxLlhWcoqu','Metin','DURMAZ','metin.durmaz@yahoo.com');

INSERT INTO user_roles(role_id, user_id) VALUES (2,1);
INSERT INTO user_roles(role_id, user_id) VALUES (1,2);

INSERT INTO userprofile(profile_id,user_id,phone,city,about,hobbies) VALUES(150,1,'+905328097162','Ankara','Who loves SQL', 'AI , C++, SQL');
INSERT INTO userprofile(profile_id,user_id,phone,city,about,hobbies) VALUES(151,2,'+905358095462','Istanbul','Who loves coding' , 'Linux , Python, NoSQL');

INSERT INTO clubcategory(category_id,clubname, description, imageurl)  VALUES (1,'Book Club','Description for Book Club','assets/img/books.jpg');
INSERT INTO clubcategory(category_id,clubname, description, imageurl)  VALUES (2,'Game Club','Description for Game Club','assets/img/game.png');
INSERT INTO clubcategory(category_id,clubname, description, imageurl)  VALUES (3,'Movie Club','Description for Movie Club','assets/img/movie.jpg');
INSERT INTO clubcategory(category_id,clubname, description, imageurl)  VALUES (4,'Sport Club','Description for Sport Club','assets/img/sport.png');
INSERT INTO clubcategory(category_id,clubname, description, imageurl)  VALUES (5,'AI Club','Description for AI Club','assets/img/ai.jpg');
INSERT INTO clubcategory(category_id,clubname, description, imageurl)  VALUES (6,'Documentary Club','Description for Documentary Club','assets/img/documentary.jpg');

INSERT INTO subclubs(subclub_id, imageurl, description, sub_club_name)  VALUES (1,'assets/img/football.png','Description for Football Subclub','Football Subclub' );
INSERT INTO subclubs(subclub_id, imageurl, description, sub_club_name)  VALUES (2,'assets/img/basketball.png','Description for Basketball Subclub','Basketball Subclub' );
INSERT INTO subclubs(subclub_id, imageurl, description, sub_club_name)  VALUES (3,'assets/img/esport.png','Description for E-Sport Subclub','E-Sport Subclub' );
INSERT INTO subclubs(subclub_id, imageurl, description, sub_club_name)  VALUES (4,'assets/img/f1.jpg','Description for F1 Subclub','F1 Subclub' );
INSERT INTO subclubs(subclub_id, imageurl, description, sub_club_name)  VALUES (5,'assets/img/ml.png','Description of the Machine Learning Subclub','Machine Learning Subclub' );
INSERT INTO subclubs(subclub_id, imageurl, description, sub_club_name)  VALUES (6,'assets/img/compvision.png','Description for Computer Vision Subclub','Computer Vision Subclub' );

INSERT INTO chat(chat_id, chat_description, subclub_id) VALUES(1,'chat-desc-1',1);
INSERT INTO chat(chat_id, chat_description, subclub_id) VALUES(2,'chat-desc-2',2);
INSERT INTO chat(chat_id, chat_description, subclub_id) VALUES(3,'chat-desc-3',3);
INSERT INTO chat(chat_id, chat_description, subclub_id) VALUES(4,'chat-desc-4',4);
INSERT INTO chat(chat_id, chat_description, subclub_id) VALUES(5,'chat-desc-5',5);
INSERT INTO chat(chat_id, chat_description, subclub_id) VALUES(6,'chat-desc-6',6);

UPDATE subclubs SET chat_id = 1 WHERE subclub_id = 1;
UPDATE subclubs SET chat_id = 2 WHERE subclub_id = 2;
UPDATE subclubs SET chat_id = 3 WHERE subclub_id = 3;
UPDATE subclubs SET chat_id = 4 WHERE subclub_id = 4;
UPDATE subclubs SET chat_id = 5 WHERE subclub_id = 5;
UPDATE subclubs SET chat_id = 6 WHERE subclub_id = 6;

INSERT INTO subclub_category(clubcategory_id, subclud_id) VALUES (4,1);
INSERT INTO subclub_category(clubcategory_id, subclud_id) VALUES (4,2);
INSERT INTO subclub_category(clubcategory_id, subclud_id) VALUES (4,3);
INSERT INTO subclub_category(clubcategory_id, subclud_id) VALUES (4,4);
INSERT INTO subclub_category(clubcategory_id, subclud_id) VALUES (5,5);
INSERT INTO subclub_category(clubcategory_id, subclud_id) VALUES (5,6);

INSERT INTO forms(form_id,baglioldugugrup) VALUES(1,1);
INSERT INTO forms(form_id,baglioldugugrup) VALUES(2,2);
INSERT INTO forms(form_id,baglioldugugrup) VALUES(3,3);
INSERT INTO forms(form_id,baglioldugugrup) VALUES(4,4);
INSERT INTO forms(form_id,baglioldugugrup) VALUES(5,5);
INSERT INTO forms(form_id,baglioldugugrup) VALUES(6,6);

UPDATE subclubs SET form_id = 1 WHERE subclub_id = 1;
UPDATE subclubs SET form_id = 2 WHERE subclub_id = 2;
UPDATE subclubs SET form_id = 3 WHERE subclub_id = 3;
UPDATE subclubs SET form_id = 4 WHERE subclub_id = 4;
UPDATE subclubs SET form_id = 5 WHERE subclub_id = 5;
UPDATE subclubs SET form_id = 6 WHERE subclub_id = 6;

INSERT INTO questions(question_id,question_content,form_id) VALUES(1,'I prefer team sports to individual sports.',1);
INSERT INTO questions(question_id,question_content,form_id) VALUES(2,'How often do you check the news about basketball ?',1);
INSERT INTO questions(question_id,question_content,form_id) VALUES(3,'I prefer athletic sports to stationary sports branches.',1);
INSERT INTO questions(question_id,question_content,form_id) VALUES(4,'How many points would you give for wanting to play basketball ?',1);
INSERT INTO questions(question_id,question_content,form_id) VALUES(5,'How often do you go to sports competitions ?',1);

INSERT INTO questions(question_id,question_content,form_id) VALUES(6,'Do you prefer team sports to individual sports ?',2);
INSERT INTO questions(question_id,question_content,form_id) VALUES(7,'How often do you check news about football ?',2);
INSERT INTO questions(question_id,question_content,form_id) VALUES(8,'If you have a favorite football team, how many points would you give your fanaticism ?',2);
INSERT INTO questions(question_id,question_content,form_id) VALUES(9,'I prefer football to other sports.',2);
INSERT INTO questions(question_id,question_content,form_id) VALUES(10,'How often do you go to sports competitions?',2);

INSERT INTO questions(question_id,question_content,form_id) VALUES(11,'How would you rate your interest with online games',3);
INSERT INTO questions(question_id,question_content,form_id) VALUES(12,'I like to spend time at the computer with my friends.',3);
INSERT INTO questions(question_id,question_content,form_id) VALUES(13,'How are you with games such as Dota 2, League of Legends, ZULA, CS: GO, Valorant ?',3);
INSERT INTO questions(question_id,question_content,form_id) VALUES(14,'The number of online games you are actively attending',3);
INSERT INTO questions(question_id,question_content,form_id) VALUES(15,'I prefer multiplayer games to single player games.',3);

INSERT INTO questions(question_id,question_content,form_id) VALUES(16,'How often do you watch Formula 1 races ? ',4);
INSERT INTO questions(question_id,question_content,form_id) VALUES(17,'How interested you are in car ?',4);
INSERT INTO questions(question_id,question_content,form_id) VALUES(18,'How many points would you give to your speed passion ?',4);
INSERT INTO questions(question_id,question_content,form_id) VALUES(19,'How ambitious you are while playing games or racing ?',4);
INSERT INTO questions(question_id,question_content,form_id) VALUES(20,'How often do you follow magazines, books, videos etc. about racing cars ?',4);

INSERT INTO questions(question_id,question_content,form_id) VALUES(21,'How interested you are in algorithm development ?',5);
INSERT INTO questions(question_id,question_content,form_id) VALUES(22,'How many points would you give your analytical thinking ability ?',5);
INSERT INTO questions(question_id,question_content,form_id) VALUES(23,'How talented you are in mathematics and statistics ?',5);
INSERT INTO questions(question_id,question_content,form_id) VALUES(24,'How interested you are in topics such as data processing and data analysis ?',5);
INSERT INTO questions(question_id,question_content,form_id) VALUES(25,'How often do you follow articles, magazines and videos about machine learning ?',5);

INSERT INTO questions(question_id,question_content,form_id) VALUES(26,'How talented you are in Programming, Algorithm Analysis and Graphics ?',6);
INSERT INTO questions(question_id,question_content,form_id) VALUES(27,'Would you rate your 3-dimensional thinking skill ?',6);
INSERT INTO questions(question_id,question_content,form_id) VALUES(28,'How interested you are in neural network,optics,neuroscience and cognitive science?',6);
INSERT INTO questions(question_id,question_content,form_id) VALUES(29,'How often do you follow articles, magazines and videos about computer vision ?',6);
INSERT INTO questions(question_id,question_content,form_id) VALUES(30,'How many points would you give your analytical thinking ability ?',6);

INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (1,1);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (1,2);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (1,3);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (1,4);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (1,5);

INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (2,6);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (2,7);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (2,8);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (2,9);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (2,10);

INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (3,11);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (3,12);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (3,13);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (3,14);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (3,15);

INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (4,16);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (4,17);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (4,18);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (4,19);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (4,20);

INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (5,21);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (5,22);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (5,23);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (5,24);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (5,25);

INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (6,26);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (6,27);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (6,28);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (6,29);
INSERT INTO forms_question_list(form_form_id, question_list_question_id) VALUES (6,30);

