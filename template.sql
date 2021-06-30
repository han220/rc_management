CREATE TABLE if not exists `Student` (
  `studentId` int PRIMARY KEY,
  `name` varchar(20),
  `roomNumber` int,
  `penaltyPoints` int DEFAULT 0,
  `semester` varchar(20),
  `rc` varchar(20),
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP
);
