DROP DATABASE TERMP;
CREATE DATABASE TERMP;
USE TERMP;

CREATE TABLE TYPE(
T_ID INT NOT NULL,
NAME VARCHAR(150),
DIST NUMERIC (50,2),
SCORE NUMERIC (50,2),
PRICE NUMERIC(50,2),
PRIMARY KEY(T_ID));

CREATE TABLE  FOOD(
F_ID INT NOT NULL,
T_ID INT,
NAME VARCHAR(150),
DIST NUMERIC (50,2),
SCORE NUMERIC (50,2),
PRICE NUMERIC(50,2),
PRIMARY KEY (F_ID),
FOREIGN KEY(T_ID) REFERENCES TYPE (T_ID));

CREATE  TABLE DESCRIPTION(
ID INT NOT NULL,
T_ID INT,
F_ID INT,
RESTAURANT VARCHAR (150),
DIST NUMERIC (50,2),
SCORE NUMERIC (50,2),
PRICE NUMERIC(50,2),
PHONE VARCHAR (150),
LOCATION VARCHAR(150),
PRIMARY KEY(ID),
FOREIGN KEY(F_ID) REFERENCES FOOD(F_ID),
FOREIGN KEY(T_ID) REFERENCES TYPE(T_ID));

INSERT INTO   TYPE VALUES (0, '한식',NULL, NULL, NULL);
INSERT INTO   TYPE VALUES (1, '중식',NULL, NULL, NULL);
INSERT INTO   TYPE VALUES (2, '일식',NULL, NULL, NULL);
INSERT INTO   TYPE VALUES (3, '분식',NULL, NULL, NULL);

INSERT INTO   FOOD VALUES (0, 0, '찜닭',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (1, 0, '보쌈',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (2, 0, '족발',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (3, 0, '냉면',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (4, 0, '비빔밥',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (5, 1, '짜장면',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (6, 1, '짬뽕',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (7, 1, '탕수육',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (8, 1, '볶음밥',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (9, 1, '잡채밥',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (10, 2, '초밥',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (11, 2, '냉모밀',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (12, 2, '우동',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (13, 2, '돈가스',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (14, 2, '덮밥',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (15, 3, '순대',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (16, 3, '튀김',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (17, 3, '라면',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (18, 3, '떡볶이',NULL, NULL, NULL);
INSERT INTO   FOOD VALUES (19, 3, '김밥',NULL, NULL, NULL);

INSERT INTO   DESCRIPTION VALUES (0, 0, 0, '내가찜한닭',1, 3.8, 17000, '031-751-5552','경기 성남시 수정구 성남대로 1334 경원프라자');
INSERT INTO   DESCRIPTION VALUES (1, 0, 0, '잡사봐찜닭보쌈',20, 4.5, 21900, '050-7457-1056','경기 성남시 수정구 수정남로 145');
INSERT INTO   DESCRIPTION VALUES (2, 0, 1,  '착한보쌈',23, 4.6, 25000, '031-722-3456','경기도 성남시 수정구 시민로133번길 11 1층');
INSERT INTO   DESCRIPTION VALUES (3, 0, 1, '장충동왕족발보쌈',30, 4.6, 25000, '050-4457-4452','경기도 성남시 수정구 시민로249번길 1');
INSERT INTO   DESCRIPTION VALUES (4, 0, 1, '잡사봐찜닭보쌈',20, 4.5, 21900, '050-7457-1056','경기 성남시 수정구 수정남로 145');
INSERT INTO   DESCRIPTION VALUES (5, 0, 2, '보쌈 앤 족발이야기',16, 2.5, 30000, '050-7458-5166','경기도 성남시 수정구 태평동');
INSERT INTO   DESCRIPTION VALUES (6, 0, 2, '장충동왕족발보쌈',30, 4.6, 25000, '050-4457-4452','경기도 성남시 수정구 시민로249번길 1');
INSERT INTO   DESCRIPTION VALUES (7, 0, 3, '신의주칡냉면 앤 수제돈까스',20, 4.3, 7000, '031-723-8777','경기도 성남시 수정구 복정로 30 ');
INSERT INTO   DESCRIPTION VALUES (8, 0, 3, '포크포크',10, 4.7, 5500, '031-751-3223 ','경기 성남시 수정구 탄리로 122');
INSERT INTO   DESCRIPTION VALUES (9, 0, 3, '개성냉면',3, 4.5, 5500, '031-722-2627 ','경기도 성남시 수정구 성남대로 1342 ');
INSERT INTO   DESCRIPTION VALUES (10, 0, 4, '원조은혜식당',30, 4.7, 6000, '031-722-1921','경기 성남시 수정구 탄리로 122');
INSERT INTO   DESCRIPTION VALUES (11, 0, 4, '싸고가장맛e있는집밥',2, 4, 5500, '031-722-4414','경기 성남시 수정구 태평로 2 104호');
INSERT INTO   DESCRIPTION VALUES (12, 0, 4, '푸드별',9, 4.6, 7000, '050-7465-0728 ','경기도 성남시 수정구 제일로 211-3 ');
INSERT INTO   DESCRIPTION VALUES (13, 1, 5, '만다린',15, 4, 4500, '031-751-4455','경기도 성남시 수정구 복정동 692-14');
INSERT INTO   DESCRIPTION VALUES (14, 1, 5, '챠이나',15, 5, 4500, '050-7458-5166','경기도 성남시 수정구 복정동 692-12');
INSERT INTO   DESCRIPTION VALUES (15, 1, 5, '청운 중화요리',2, 4.7, 7000, '050-7987-1077','경기도 성남시 수정구 성남대로1249번길 6');
INSERT INTO   DESCRIPTION VALUES (16, 1, 6, '만다린',15, 4, 5500, '031-751-4455','경기도 성남시 수정구 복정동 692-14');
INSERT INTO   DESCRIPTION VALUES (17, 1, 6, '챠이나',15, 5, 5500, '050-7458-5166','경기도 성남시 수정구 복정동 692-12');
INSERT INTO   DESCRIPTION VALUES (18, 1, 6, '청운 중화요리',2, 4.7, 5500, '050-7987-1077','경기도 성남시 수정구 성남대로1249번길 6');
INSERT INTO   DESCRIPTION VALUES (19, 1, 7, '만다린',15, 4, 14000, '031-751-4455','경기도 성남시 수정구 복정동 692-14');
INSERT INTO   DESCRIPTION VALUES (20, 1, 7, '챠이나',15, 5, 13000, '050-7458-5166','경기도 성남시 수정구 복정동 692-12');
INSERT INTO   DESCRIPTION VALUES (21, 1, 7, '청운 중화요리',2, 4.7, 15000, '050-7987-1077','경기도 성남시 수정구 성남대로1249번길 6');
INSERT INTO   DESCRIPTION VALUES (22, 1, 8, '만다린',15, 4, 6000, '031-751-4455','경기도 성남시 수정구 복정동 692-14');
INSERT INTO   DESCRIPTION VALUES (23, 1, 8, '챠이나',15, 5, 6000, '050-7458-5166','경기도 성남시 수정구 복정동 692-12');
INSERT INTO   DESCRIPTION VALUES (24, 1, 9, '만다린',15, 4, 6500, '031-751-4455','경기도 성남시 수정구 복정동 692-14');
INSERT INTO   DESCRIPTION VALUES (25, 1, 9, '챠이나',15, 5, 6000, '050-7458-5166','경기도 성남시 수정구 복정동 692-12');
INSERT INTO   DESCRIPTION VALUES (26, 2, 10, '야미가',15, 4.6, 8500, '031-759-0808','경기도 성남시 수정구 제일로180번길 3');
INSERT INTO   DESCRIPTION VALUES (27, 2, 10, '미다미',34, 4.4, 8000, '050-4454-8232','경기도 성남시 수정구 탄리로30번길 15-1');
INSERT INTO   DESCRIPTION VALUES (28, 2, 11, '야미가',15, 4.6, 6000, '031-759-0808','경기도 성남시 수정구 제일로180번길 3');
INSERT INTO   DESCRIPTION VALUES (29, 2, 11, '가츠야',26, 5.0, 5500, '031-754-5057','경기도 성남시 수정구 성남대로1518번길 10');
INSERT INTO   DESCRIPTION VALUES (30, 2, 12, '오니기리와이규동',1, 4.3, 4900, '031-758-5290','경기도 성남시 수정구 성남대로 1342');
INSERT INTO   DESCRIPTION VALUES (31, 2, 12, '야미가',15, 4.6, 4500, '031-759-0808','경기도 성남시 수정구 제일로180번길 3');
INSERT INTO   DESCRIPTION VALUES (32, 2, 12, '그집김밥',5, 4.6, 5000, '031-722-5553','경기도 성남시 수정구 성남대로 1330');
INSERT INTO   DESCRIPTION VALUES (33, 2, 13, '정수야돈까스',24, 4.6, 7000, '050-4454-9442','경기도 성남시 수정구 수정남로28번길 2');
INSERT INTO   DESCRIPTION VALUES (34, 2, 13, '오니기리와이규동',1, 4.3, 6500, '031-758-5290','경기도 성남시 수정구 성남대로 1342');
INSERT INTO   DESCRIPTION VALUES (35, 2, 13, '그집김밥',5, 4.6, 6500, '031-722-5553','경기도 성남시 수정구 성남대로 1330');
INSERT INTO   DESCRIPTION VALUES (36, 2, 14, '야미가',15, 4.6, 6500, '031-759-0808','경기도 성남시 수정구 제일로180번길 3');
INSERT INTO   DESCRIPTION VALUES (37, 2, 14, '그집김밥',5, 4.6, 6500, '031-722-5553','경기도 성남시 수정구 성남대로 1330');
INSERT INTO   DESCRIPTION VALUES (38, 2, 14, '오니기리와이규동',1, 4.3, 5900, '031-758-5290','경기도 성남시 수정구 성남대로 1342');
INSERT INTO   DESCRIPTION VALUES (39, 3, 15, '동대문땡초불닭발엽기떡볶이',45, 4.2, 3000, '050-4457-8594','경기도 성남시 중원구 산성대로 258-1');
INSERT INTO   DESCRIPTION VALUES (40, 3, 15, '그집김밥',5, 4.6, 3500, '031-722-5553','경기도 성남시 수정구 성남대로 1330');
INSERT INTO   DESCRIPTION VALUES (41, 3, 16, '신전떡볶이',11, 3.6, 1500, '050-7458-9390','경기도 성남시 수정구 복정로42번길 1');
INSERT INTO   DESCRIPTION VALUES (42, 3, 16, '쩡이떡볶이',1, 4.5, 2500, '031-721-4632','경기도 성남시 수정구 성남대로 1342 가천대학교');
INSERT INTO   DESCRIPTION VALUES (43, 3, 17, '쩡이떡볶이',1, 4.5, 10000, '031-721-4632','경기도 성남시 수정구 성남대로 1342 가천대학교');
INSERT INTO   DESCRIPTION VALUES (44, 3, 17, '이가네돈까스',4, 3.8, 3000, '050-4570-5533','경기도 성남시 수정구 성남대로 1334 경원프라자 110호 이가네돈까스');
INSERT INTO   DESCRIPTION VALUES (45, 3, 17, '그집김밥',5, 4.6, 2500, '031-722-5553','경기도 성남시 수정구 성남대로 1330');
INSERT INTO   DESCRIPTION VALUES (46, 3, 18, '동대문땡초불닭발엽기떡볶이',45, 4.2, 14000, '050-4457-8594','경기도 성남시 중원구 산성대로 258-1');
INSERT INTO   DESCRIPTION VALUES (47, 3, 18, '신전떡볶이',11, 3.6, 2500, '050-7458-9390','경기도 성남시 수정구 복정로42번길 1');
INSERT INTO   DESCRIPTION VALUES (48, 3, 18, '경원분식',1, 4.7, 3000, 'xxx-xxxx-xxxx','경기도 성남시 수정구 태평1동 경원프라자 지하4층');
INSERT INTO   DESCRIPTION VALUES (49, 3, 19, '신전떡볶이',11, 3.6, 2000, '050-7458-9390','경기도 성남시 수정구 복정로42번길 1');
INSERT INTO   DESCRIPTION VALUES (50, 3, 19, '그집김밥',5, 4.6, 2500, '031-722-5553','경기도 성남시 수정구 성남대로 1330');
INSERT INTO   DESCRIPTION VALUES (51, 3, 19, '이가네돈까스',4, 4.7, 2500, '050-4570-5533','경기도 성남시 수정구 성남대로 1334 경원프라자 110호 이가네돈까스');


update food as F set dist = (select min(dist) from description as D group by F_ID having D.F_ID = F.F_ID);
update food as F set score = (select min(score) from description as D group by F_ID having D.F_ID = F.F_ID);
update food as F set price = (select avg(price) from description as D group by F_ID having D.F_ID = F.F_ID);

update type as T set dist = (select avg(dist) from description as D group by T_ID having D.T_ID = T.T_ID);
update type as T set score = (select avg(score) from description as D group by T_ID having D.T_ID = T.T_ID);
update type as T set price = (select avg(price) from description as D group by T_ID having D.T_ID = T.T_ID);


COMMIT;