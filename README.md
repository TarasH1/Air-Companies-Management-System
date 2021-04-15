# Air-Companies-Management-System

API Endpoints:

MySQL Data:

Air Company:

UPDATE `acms`.`aircompany` SET `founded_at` = 'Chicago, 1960' WHERE (`id` = '1');
UPDATE `acms`.`aircompany` SET `founded_at` = 'Boston, 2001' WHERE (`id` = '2');

Airplane:

UPDATE `acms`.`airplane` SET `created_at` = '2017-11-15', `number_of_flights` = '400', `type` = 'Passenger ' WHERE (`id` = '1');
UPDATE `acms`.`airplane` SET `created_at` = '2015-11-15', `type` = 'Cargo' WHERE (`id` = '2');

Flight:

INSERT INTO `acms`.`flight` (`id`, `created_at`, `delay_started_at`, `departure_country`, `destination_country`, `distance`, `ended_at`, `estimated_flight_time`, `flight_status`) VALUES ('1', '10:34:54.1237', '12:34:54.1237', 'Ukraine', 'Italy', '3000', '16:34:54.1237', '04:35:00.0000', '0');

