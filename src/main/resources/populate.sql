INSERT INTO roles(role_id,role_name)  VALUES (1,'USER');
INSERT INTO roles(role_id,role_name)  VALUES (2,'ADMIN');
INSERT INTO roles(role_id,role_name)  VALUES (3,'SUBCLUBADMIN');

INSERT INTO clubcategory(category_id,clubname, description, imageurl)  VALUES (1,'Book Club','Description for Book Club','assets/img/books.jpg');
INSERT INTO clubcategory(category_id,clubname, description, imageurl)  VALUES (2,'Game Club','Description for Game Club','assets/img/game.png');
INSERT INTO clubcategory(category_id,clubname, description, imageurl)  VALUES (3,'Movie Club','Description for Movie Club','assets/img/movie.jpg');
INSERT INTO clubcategory(category_id,clubname, description, imageurl)  VALUES (4,'Sport Club','Description for Sport Club','assets/img/sport.png');
INSERT INTO clubcategory(category_id,clubname, description, imageurl)  VALUES (5,'AI Club','Description for AI Club','assets/img/ai.jpg');
INSERT INTO clubcategory(category_id,clubname, description, imageurl)  VALUES (6,'Documentary Club','Description for Documentary Club','assets/img/documentary.jpg');


INSERT INTO subclubs(subclub_id, imageurl, description, sub_club_name)  VALUES (1,'assets/img/football.png','Description for Football Subclub','Football Subclub');
INSERT INTO subclubs(subclub_id, imageurl, description, sub_club_name)  VALUES (2,'assets/img/basketball.png','Description for Basketball Subclub','Basketball Subclub');
INSERT INTO subclubs(subclub_id, imageurl, description, sub_club_name)  VALUES (3,'assets/img/esport.png','Description for E-Sport Subclub','E-Sport Subclub');
INSERT INTO subclubs(subclub_id, imageurl, description, sub_club_name)  VALUES (4,'assets/img/f1.jpg','Description for F1 Subclub','F1 Subclub');
INSERT INTO subclubs(subclub_id, imageurl, description, sub_club_name)  VALUES (5,'assets/img/ml.png','Description of the Machine Learning Subclub','Machine Learning Subclub');
INSERT INTO subclubs(subclub_id, imageurl, description, sub_club_name)  VALUES (6,'assets/img/compvision.png','Description for Computer Vision Subclub','Computer Vision Subclub');


INSERT INTO subclub_category(clubcategory_id, subclud_id) VALUES (4,1);
INSERT INTO subclub_category(clubcategory_id, subclud_id) VALUES (4,2);
INSERT INTO subclub_category(clubcategory_id, subclud_id) VALUES (4,3);
INSERT INTO subclub_category(clubcategory_id, subclud_id) VALUES (4,4);
INSERT INTO subclub_category(clubcategory_id, subclud_id) VALUES (5,5);
INSERT INTO subclub_category(clubcategory_id, subclud_id) VALUES (5,6);



