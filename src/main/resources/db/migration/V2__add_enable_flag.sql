ALTER TABLE `category` ADD `enabled` BOOLEAN NOT NULL DEFAULT TRUE AFTER `thumbnail`;
ALTER TABLE `subcategory` ADD `enabled` BOOLEAN NOT NULL DEFAULT TRUE AFTER `description_fr`; 
ALTER TABLE `collection` ADD `enabled` BOOLEAN NOT NULL DEFAULT TRUE AFTER `subcategory_id`; 