USE prmody;

DROP table Test;
DROP table Medical_Record;
DROP table BillingAccount;
DROP table Beds;
DROP table Ward;
DROP table Patient;
DROP table Staff;


CREATE TABLE Staff(

staff_id INT AUTO_INCREMENT PRIMARY KEY,

name NCHAR(50) NOT NULL,

age INT NOT NULL NOT NULL,

gender NCHAR(7) NOT NULL,

job_title NCHAR(50) NOT NULL,

prof_title NCHAR(50) ,

department NCHAR(50) NOT NULL,

phone NVARCHAR(15) NOT NULL,

address NVARCHAR(100) NOT NULL,

log_in_cred NVARCHAR(30) NOT NULL

);

CREATE TABLE Patient(

patient_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,

SSN  NVARCHAR(15),

name NCHAR(50) NOT NULL,

dob DATE NOT NULL,

gender NCHAR(7) NOT NULL,

age INT NOT NULL,

phone NVARCHAR(15) NOT NULL,

address NVARCHAR(100) NOT NULL,

treatmentPlan INT NOT NULL,

status  NCHAR(5) NOT NULL

);



CREATE TABLE Ward(

WardNumber INT PRIMARY KEY,

Capacity  INT NOT NULL,

Charges INT NOT NULL,

responsible_nurse INT NOT NULL,

FOREIGN KEY (responsible_nurse) REFERENCES Staff(staff_id) ON DELETE RESTRICT

);



CREATE TABLE Beds(

WardNumber INT NOT NULL,

BedNumber INT NOT NULL,

patient_id INT,

start_date DATE,

end_date DATE,

PRIMARY KEY (WardNumber, BedNumber),

FOREIGN KEY(patient_id) REFERENCES Patient(patient_id) ON DELETE SET NULL,

FOREIGN KEY(WardNumber) REFERENCES Ward(WardNumber) ON DELETE CASCADE


);



CREATE TABLE BillingAccount(

visit_date DATE  NOT NULL,

SSN NVARCHAR(15) NOT NULL,

patient_id INT,

address NVARCHAR(100) NOT NULL,

ward_charge INT  NOT NULL DEFAULT 0,

test_charge INT  NOT NULL DEFAULT 0,

treatment_charge INT  NOT NULL DEFAULT 0,

registration_fees INT  NOT NULL DEFAULT 0,

payment_method NVARCHAR(15)  NOT NULL,

cardNumber NVARCHAR(20),

PRIMARY KEY (visit_date, patient_id),

FOREIGN KEY (patient_id) REFERENCES Patient(patient_id) ON DELETE CASCADE

);



CREATE TABLE Medical_Record(

record_id INT AUTO_INCREMENT PRIMARY KEY,

patient_id INT NOT NULL,

staff_id INT,

start_date DATE  NOT NULL,

end_date DATE ,

prescription NVARCHAR(200),

diagnosis_details NVARCHAR(500) NOT NULL default "To be diagnosed",

treatment_charge INT  NOT NULL DEFAULT 0,

FOREIGN KEY (patient_id) REFERENCES Patient(patient_id) ON DELETE CASCADE,

FOREIGN KEY (staff_id) REFERENCES Staff(staff_id) ON DELETE SET NULL

);

CREATE TABLE Test(

staff_id INT,

record_id INT NOT NULL,

time DATETIME NOT NULL,

name NVARCHAR(25) NOT NULL,

charges INT NOT NULL DEFAULT 0,

PRIMARY KEY (record_id, time),

FOREIGN KEY (record_id) REFERENCES Medical_Record(record_id) ON DELETE CASCADE,

FOREIGN KEY (staff_id) REFERENCES Staff(Staff_id) ON DELETE SET NULL

);

INSERT INTO Staff VALUES (      100     ,       'Mary'  ,       40      ,       'Female'        ,       'Doctor'               ,        'senior'            , 'Neurology'       ,       '654'           ,       '90 ABC St , Raleigh NC 27'             ,'TopSecret1'   );

INSERT INTO Staff VALUES (      101     ,       'John'  ,   45  ,   'Male'          ,   'Billingstaff'     ,    NULL            , 'Office'          ,   '564'           ,       '798 XYZ St , Rochester NY 54'  ,'TopSecret2'   );

INSERT INTO Staff VALUES (      102     ,       'Carol' ,       55      ,       'Female'        ,       'Nurse'            ,    NULL                , 'ER'              ,       '911'           ,       '351 MH St , Greensboro NC 27'  ,'TopSecret3'   );

INSERT INTO Staff VALUES (      103     ,       'Emma'  ,       55      ,       'Female'        ,       'Doctor'               ,        'Senior surgeon', 'Oncological Surgery' ,'546'      ,   '49 ABC St , Raleigh NC 27'         ,'TopSecret4'       );

INSERT INTO Staff VALUES (      104     ,       'Ava'   ,       55      ,       'Female'        ,       'Front Desk Staff' ,    NULL            , 'Office'          ,   '777'           ,       '425 RG St , Raleigh NC 27'         ,'TopSecret5'       );

INSERT INTO Staff VALUES (      105     ,       'Peter' ,       52      ,       'Male'      ,   'Doctor'               ,        'Anesthetist'   , 'Oncological Surgery' ,       '724'   ,       '475 RG St , Raleigh NC 27'         ,'TopSecret6'       );

INSERT INTO Staff VALUES (      106     ,       'Olivia',       27      ,       'Female'        ,       'Nurse'            ,    NULL            , 'Neurology'   ,       '799'           ,       '325 PD St , Raleigh NC 27'         ,'TopSecret7'       );



INSERT INTO Patient VALUES (1001,'000-01-1234','David','1980-01-30','Male',39,'919-123-3324','69 ABC St , Raleigh NC 27730',20,'no');

INSERT INTO Patient VALUES (1002,'000-02-1234','Sarah','1971-01-30','Female',48,'919-563-3478','81 DEF St , Cary NC 27519',20,'no');

INSERT INTO Patient VALUES (1003,'000-03-1234','Joseph','1987-01-30','Male',32,'919-957-2199','31 OPG St , Cary NC 27519',10,'no');

INSERT INTO Patient VALUES (1004,'000-04-1234','Lucy','1985-01-30','Female',34,'919-838-7123','10 TBC St , Raleigh NC 27730',5,'yes');


INSERT INTO Medical_Record VALUES (1,1001,100,'2019-03-01', NULL,'nervine','Hospitalization', 0);

INSERT INTO Medical_Record VALUES (2,1002,100,'2019-03-10', NULL,'nervine','Hospitalization', 0);

INSERT INTO Medical_Record VALUES (3,1003,100,'2019-03-15', NULL,'nervine','Hospitalization', 0);

INSERT INTO Medical_Record VALUES (4,1004,103,'2019-03-17', '2019-03-21','analgesic','Surgeon, Hospitalization', 0);



INSERT INTO Ward VALUES (001,4,50, 102);

INSERT INTO Ward VALUES (002,4,50, 102);

INSERT INTO Ward VALUES (003,2,100, 106);

INSERT INTO Ward VALUES (004,2,100, 106);




INSERT INTO Beds VALUES (001,1,1001,"2019-03-01", NULL);

INSERT INTO Beds VALUES (001,2,NULL, NULL, NULL);

INSERT INTO Beds VALUES (001,3,NULL, NULL, NULL);

INSERT INTO Beds VALUES (001,4,NULL, NULL, NULL);



INSERT INTO Beds VALUES (002,1,1002, "2019-03-10", NULL);

INSERT INTO Beds VALUES (002,2,NULL, NULL, NULL);

INSERT INTO Beds VALUES (002,3,NULL, NULL, NULL);
INSERT INTO Beds VALUES (002,4,NULL, NULL, NULL);



INSERT INTO Beds VALUES (003,1,1004, "2019-03-17", "2019-03-21");

INSERT INTO Beds VALUES (003,2,NULL, NULL, NULL);

INSERT INTO Beds VALUES (004,1,NULL, NULL, NULL);
INSERT INTO Beds VALUES (004,2,NULL, NULL, NULL);




INSERT INTO BillingAccount VALUES ('2019-03-01','000-01-1234',1001,'69 TBC St. Raleigh NC 27730',0,0,0,100,'Credit Card','4044987612349123');

INSERT INTO BillingAccount VALUES ('2019-03-10','000-02-1234',1002,'81 DEF St. Cary NC 27519',0,0,0,100,'Credit Card','4401982398541143');

INSERT INTO BillingAccount VALUES ('2019-03-15','000-03-1234',1003,'31 OPG St. Cary NC 27519',0,0,0,100,'Check',NULL);

INSERT INTO BillingAccount VALUES ('2019-03-17','000-04-1234',1004,'10 TBC St. Raleigh NC 27730',400,0,0,100,'Credit Card','4044987612349123');


SELECT * FROM Ward;

SELECT * FROM Staff;

SELECT * FROM Patient;

SELECT * FROM Beds;

SELECT * FROM BillingAccount;

SELECT * FROM Medical_Record;

SELECT * FROM Test;
