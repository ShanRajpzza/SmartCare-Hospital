-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: smartcare_db
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary view structure for view `active_doctors_directory`
--

DROP TABLE IF EXISTS `active_doctors_directory`;
/*!50001 DROP VIEW IF EXISTS `active_doctors_directory`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `active_doctors_directory` AS SELECT 
 1 AS `Doctor_Name`,
 1 AS `Specialization`,
 1 AS `Consultation_Fee`,
 1 AS `Department_Name`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `admission`
--

DROP TABLE IF EXISTS `admission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admission` (
  `Admission_ID` varchar(20) NOT NULL,
  `Admission_Date` date NOT NULL,
  `Discharge_Date` date NOT NULL,
  `Bed_Number` varchar(10) NOT NULL,
  `admission_status` varchar(255) DEFAULT NULL,
  `Patient_ID` varchar(20) NOT NULL,
  `Room_ID` varchar(20) NOT NULL,
  PRIMARY KEY (`Admission_ID`),
  KEY `FK_Admission_Patient` (`Patient_ID`),
  KEY `FK_Admission_Room` (`Room_ID`),
  CONSTRAINT `FK_Admission_Patient` FOREIGN KEY (`Patient_ID`) REFERENCES `patient` (`Patient_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Admission_Room` FOREIGN KEY (`Room_ID`) REFERENCES `room` (`Room_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admission`
--

LOCK TABLES `admission` WRITE;
/*!40000 ALTER TABLE `admission` DISABLE KEYS */;
INSERT INTO `admission` VALUES ('ADM001','2026-08-01','2026-08-05','B-12','Discharged','P001','R001'),('ADM002','2026-08-08','2026-08-15','B-05','Admitted','P002','R002'),('ADM003','2026-08-03','2026-08-07','G-01','Discharged','P003','R003'),('ADM004','2026-08-09','2026-08-12','ICU-02','Transferred','P004','R004'),('ADM005','2026-08-06','2026-08-06','S-04','Cancelled','P005','R005');
/*!40000 ALTER TABLE `admission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `Appointment_ID` varchar(20) NOT NULL,
  `Appointment_Date` date NOT NULL,
  `Appointment_Time` time NOT NULL,
  `Consultation_Room` varchar(20) DEFAULT NULL,
  `appointment_status` varchar(255) DEFAULT NULL,
  `Patient_ID` varchar(20) NOT NULL,
  `Doctor_ID` varchar(20) NOT NULL,
  PRIMARY KEY (`Appointment_ID`),
  KEY `FK_Appointment_Patient` (`Patient_ID`),
  KEY `FK_Appointment_Doctor` (`Doctor_ID`),
  CONSTRAINT `FK_Appointment_Doctor` FOREIGN KEY (`Doctor_ID`) REFERENCES `doctor` (`Doctor_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Appointment_Patient` FOREIGN KEY (`Patient_ID`) REFERENCES `patient` (`Patient_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES ('APP001','2026-08-12','09:00:00','Room 101','Available','P001','DOC001'),('APP002','2026-08-12','09:30:00','Room 101','Available','P002','DOC001'),('APP003','2026-08-12','10:00:00','Room 101','Occupied','P003','DOC001'),('APP004','2026-08-12','10:30:00','Room 102','Maintenance','P004','DOC002'),('APP005','2026-08-12','11:00:00','Room 102','Available','P005','DOC002'),('APP006','2026-08-13','09:00:00','Room 103','Occupied','P006','DOC003'),('APP007','2026-08-13','09:30:00','Room 103','Occupied','P007','DOC003'),('APP008','2026-08-13','10:00:00','Room 104','Available','P008','DOC004'),('APP009','2026-08-13','10:30:00','Room 104','Occupied','P009','DOC004'),('APP010','2026-08-14','09:00:00','Room 105','Occupied','P010','DOC005'),('APP011','2026-08-14','09:30:00','Room 105','Occupied','P001','DOC005'),('APP012','2026-08-14','10:00:00','Room 101','Occupied','P002','DOC001'),('APP013','2026-08-14','10:30:00','Room 102','Available','P003','DOC002'),('APP014','2026-08-15','09:00:00','Room 103','Available','P004','DOC003'),('APP015','2026-08-15','09:30:00','Room 104','Occupied','P005','DOC004');
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `Bill_ID` varchar(20) NOT NULL,
  `Bill_Date` date NOT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `total_amount` decimal(38,2) DEFAULT NULL,
  `Patient_ID` varchar(20) NOT NULL,
  PRIMARY KEY (`Bill_ID`),
  KEY `FK_Bill_Patient` (`Patient_ID`),
  CONSTRAINT `FK_Bill_Patient` FOREIGN KEY (`Patient_ID`) REFERENCES `patient` (`Patient_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES ('B999','2026-08-23','Cash','Unpaid',0.00,'P001'),('BIL001','2026-08-05','Card','Paid',65000.00,'P001'),('BIL002','2026-08-09','Cash','Unpaid',22000.00,'P002'),('BIL003','2026-08-07','Card','Paid',15000.00,'P003'),('BIL004','2026-08-10','Cash','Unpaid',45000.00,'P004'),('BIL005','2026-08-08','Cash','Paid',8500.00,'P005'),('BIL006','2026-08-09','Card','Paid',12000.00,'P006'),('BIL007','2026-08-10','Cash','Unpaid',5500.00,'P007'),('BIL008','2026-08-11','Card','Paid',18500.00,'P008'),('BIL009','2026-08-11','Card','Paid',32000.00,'P009'),('BIL010','2026-08-12','Cash','Unpaid',9500.00,'P010');
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `Department_ID` varchar(20) NOT NULL,
  `Department_Name` varchar(100) NOT NULL,
  `Location` varchar(100) DEFAULT NULL,
  `Head_Doctor_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Department_ID`),
  KEY `FK_Department_HeadDoctor` (`Head_Doctor_ID`),
  CONSTRAINT `FK_Department_HeadDoctor` FOREIGN KEY (`Head_Doctor_ID`) REFERENCES `doctor` (`Doctor_ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('DEP001','Cardiology','Building A - Floor 2','DOC001'),('DEP002','Neurology','Building B - Floor 1','DOC002'),('DEP003','Pediatrics','Building C - Floor 1','DOC003'),('DEP004','Orthopedics','Building A - Floor 3','DOC004'),('DEP005','Dermatology','Building B - Floor 2','DOC005'),('DEP099','Neurology Unit','Building A - Floor 1','DOC010');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `Doctor_ID` varchar(20) NOT NULL,
  `doctor_name` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `Qualification` varchar(50) NOT NULL,
  `Specialization` varchar(50) NOT NULL,
  `Consultation_Fee` decimal(10,2) NOT NULL,
  `Department_ID` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Doctor_ID`),
  KEY `FK_Doctor_Department` (`Department_ID`),
  CONSTRAINT `FK_Doctor_Department` FOREIGN KEY (`Department_ID`) REFERENCES `department` (`Department_ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES ('DOC001','Dr. Saman Jayasinghe','0778889900','MBBS, MD','Cardiologist',3500.00,'DEP001',NULL,NULL,NULL),('DOC002','Dr. Anusha Wickramasinghe','0779990011','MBBS, MS','Neurologist',4000.00,'DEP002',NULL,NULL,NULL),('DOC003','Dr. Kasun Gunawardena','0770001122','MBBS, DCH','Pediatrician',3000.00,'DEP003',NULL,NULL,NULL),('DOC004','Dr. Nimal Mendis','0771112233','MBBS, MS (Orthopedics)','Orthopedic Surgeon',3800.00,'DEP004',NULL,NULL,NULL),('DOC005','Dr. Chitra Samarasinghe','0772223344','MBBS, MD (Dermatology)','Dermatologist',3200.00,'DEP005',NULL,NULL,NULL),('DOC010','Dr. Sunethra Fernando','0711223344','MBBS, MD','Neurologist',4500.00,'DEP002','123, Galle Road, Colombo','1985-05-15','Female');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laboratory_test`
--

DROP TABLE IF EXISTS `laboratory_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `laboratory_test` (
  `Lab_Test_ID` varchar(20) NOT NULL,
  `Test_Name` varchar(50) NOT NULL,
  `Test_Date` date NOT NULL,
  `Test_Result` text NOT NULL,
  `Test_Status` varchar(20) DEFAULT NULL,
  `technician_name` varchar(255) DEFAULT NULL,
  `test_charge` decimal(38,2) DEFAULT NULL,
  `Patient_ID` varchar(20) NOT NULL,
  `Doctor_ID` varchar(20) NOT NULL,
  PRIMARY KEY (`Lab_Test_ID`),
  KEY `FK_LabTest_Patient` (`Patient_ID`),
  KEY `FK_LabTest_Doctor` (`Doctor_ID`),
  CONSTRAINT `FK_LabTest_Doctor` FOREIGN KEY (`Doctor_ID`) REFERENCES `doctor` (`Doctor_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_LabTest_Patient` FOREIGN KEY (`Patient_ID`) REFERENCES `patient` (`Patient_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboratory_test`
--

LOCK TABLES `laboratory_test` WRITE;
/*!40000 ALTER TABLE `laboratory_test` DISABLE KEYS */;
INSERT INTO `laboratory_test` VALUES ('LAB001','Full Blood Count','2026-08-02','Normal','Pending','Completed',2500.00,'P001','DOC001'),('LAB002','MRI Brain Scan','2026-08-09','Abnormal','In-Progress','Sample Collected',18000.00,'P002','DOC002'),('LAB003','Fasting Blood Sugar','2026-08-04','High Sugar Level','Completed','Completed',1500.00,'P003','DOC003'),('LAB004','X-Ray Chest','2026-08-10','Pending','In-Progress','In Progress',3500.00,'P004','DOC004'),('LAB005','Lipid Profile','2026-08-07','Normal','Completed','Completed',3000.00,'P005','DOC005'),('LAB006','Blood Sugar Fasting','2026-08-20','Pending','Pending','In Progress',1200.00,'P001','DOC001');
/*!40000 ALTER TABLE `laboratory_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicine` (
  `Medicine_ID` varchar(20) NOT NULL,
  `Medicine_Name` varchar(50) NOT NULL,
  `unit_price` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`Medicine_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
INSERT INTO `medicine` VALUES ('M001','Paracetamol 500mg',15.00),('M002','Amoxicillin 250mg',45.00),('M003','Omeprazole 20mg',30.00),('M004','Metformin 500mg',20.00),('M005','Aspirin 75mg',25.00),('M099','Vitamin D3',45.00);
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `Patient_ID` varchar(20) NOT NULL,
  `Full_Name` varchar(100) NOT NULL,
  `DOB` date NOT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `Address` varchar(100) NOT NULL,
  `Blood_Group` varchar(5) NOT NULL,
  `Emergency_Contact` varchar(10) NOT NULL,
  `Contact_Number` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Patient_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES ('P001','Kamal Perera','1985-05-12','Male','Colombo 03','A+','0771234567','0711234567'),('P002','Nimali Silva','1992-08-25','Female','Kandy','B+','0772345678','0712345678'),('P003','Sunil Fernando','1978-11-03','Male','Galle','O-','0773456789','0713456789'),('P004','Samanthi Wickramasinghe','1995-03-15','Female','Negombo','AB+','0774567890','0714567890'),('P005','Ruwan Jayasinghe','1980-12-20','Male','Kurunegala','O+','0775678901','0715678901'),('P006','Dilhani Gunawardena','1988-07-08','Female','Gampaha','A-','0776789012','0716789012'),('P007','Kasun Rajapaksha','2000-01-30','Male','Matara','B-','0777890123','0717890123'),('P008','Anusha Mendis','1993-09-18','Female','Kalutara','O+','0778901234','0718901234'),('P009','Mahesh Fonseka','1975-04-22','Male','Ratnapura','A+','0779012345','0719012345'),('P010','Chathurika De Silva','1997-10-05','Female','Panadura','AB-','0770123456','0710123456'),('PAT001','','1995-10-20','Male','Kandy','O+','0719876543','0771234567');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `patient_billing_summary`
--

DROP TABLE IF EXISTS `patient_billing_summary`;
/*!50001 DROP VIEW IF EXISTS `patient_billing_summary`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `patient_billing_summary` AS SELECT 
 1 AS `Full_Name`,
 1 AS `Bill_ID`,
 1 AS `Bill_Date`,
 1 AS `Total_Amount`,
 1 AS `Payment_Status`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `Room_ID` varchar(20) NOT NULL,
  `Category` varchar(20) NOT NULL,
  `availability` varchar(255) DEFAULT NULL,
  `room_charge` decimal(38,2) NOT NULL,
  PRIMARY KEY (`Room_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('R001','Luxury','Available',15000.00),('R002','Semi-Luxury','Available',10000.00),('R003','General','Not Available',5000.00),('R004','ICU','Available',25000.00),('R005','Standard','Available',7500.00);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatment`
--

DROP TABLE IF EXISTS `treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treatment` (
  `Treatment_ID` varchar(20) NOT NULL,
  `Diagnosis` varchar(100) DEFAULT NULL,
  `Prescription` varchar(100) NOT NULL,
  `Treatment_Date` date NOT NULL,
  `Treatment_Notes` text,
  `Patient_ID` varchar(20) NOT NULL,
  `Doctor_ID` varchar(20) NOT NULL,
  PRIMARY KEY (`Treatment_ID`),
  KEY `FK_Treatment_Patient` (`Patient_ID`),
  KEY `FK_Treatment_Doctor` (`Doctor_ID`),
  CONSTRAINT `FK_Treatment_Doctor` FOREIGN KEY (`Doctor_ID`) REFERENCES `doctor` (`Doctor_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Treatment_Patient` FOREIGN KEY (`Patient_ID`) REFERENCES `patient` (`Patient_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment`
--

LOCK TABLES `treatment` WRITE;
/*!40000 ALTER TABLE `treatment` DISABLE KEYS */;
INSERT INTO `treatment` VALUES ('TRT001','Hypertension (Updated)','Tab. Enalapril 10mg daily','2026-08-22','Patient responding well to treatment, dose increased','P001','DOC001'),('TRT002','Migraine','Tab. Paracetamol 500mg BD','2026-08-09','Advised to rest in a quiet room','P002','DOC002'),('TRT003','Type 2 Diabetes','Tab. Metformin 500mg BD','2026-08-04','Dietary controls recommended','P003','DOC003'),('TRT004','Fracture','Plaster cast applied','2026-08-10','Follow up after 4 weeks','P004','DOC004'),('TRT005','Eczema','Ointment application BD','2026-08-07','Skin rash reduced','P005','DOC005'),('TRT006','Viral Fever','Paracetamol','2026-08-10','Needs rest','P001','DOC003');
/*!40000 ALTER TABLE `treatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatment_medicine`
--

DROP TABLE IF EXISTS `treatment_medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treatment_medicine` (
  `Treatment_ID` varchar(20) NOT NULL,
  `Medicine_ID` varchar(20) NOT NULL,
  PRIMARY KEY (`Treatment_ID`,`Medicine_ID`),
  KEY `Medicine_ID` (`Medicine_ID`),
  CONSTRAINT `treatment_medicine_ibfk_1` FOREIGN KEY (`Treatment_ID`) REFERENCES `treatment` (`Treatment_ID`),
  CONSTRAINT `treatment_medicine_ibfk_2` FOREIGN KEY (`Medicine_ID`) REFERENCES `medicine` (`Medicine_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment_medicine`
--

LOCK TABLES `treatment_medicine` WRITE;
/*!40000 ALTER TABLE `treatment_medicine` DISABLE KEYS */;
INSERT INTO `treatment_medicine` VALUES ('TRT001','M001'),('TRT002','M001'),('TRT004','M001'),('TRT006','M001'),('TRT001','M002'),('TRT002','M002'),('TRT001','M003'),('TRT003','M004'),('TRT005','M005');
/*!40000 ALTER TABLE `treatment_medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `active_doctors_directory`
--

/*!50001 DROP VIEW IF EXISTS `active_doctors_directory`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `active_doctors_directory` AS select `d`.`doctor_name` AS `Doctor_Name`,`d`.`Specialization` AS `Specialization`,`d`.`Consultation_Fee` AS `Consultation_Fee`,`dept`.`Department_Name` AS `Department_Name` from (`doctor` `d` join `department` `dept` on((`d`.`Department_ID` = `dept`.`Department_ID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `patient_billing_summary`
--

/*!50001 DROP VIEW IF EXISTS `patient_billing_summary`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `patient_billing_summary` AS select `p`.`Full_Name` AS `Full_Name`,`b`.`Bill_ID` AS `Bill_ID`,`b`.`Bill_Date` AS `Bill_Date`,`b`.`total_amount` AS `Total_Amount`,`b`.`payment_status` AS `Payment_Status` from (`patient` `p` join `bill` `b` on((`p`.`Patient_ID` = `b`.`Patient_ID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-08-23 20:43:23
