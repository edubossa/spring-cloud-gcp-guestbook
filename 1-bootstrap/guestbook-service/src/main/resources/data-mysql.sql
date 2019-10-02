CREATE TABLE guestbook_message (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name CHAR(128) NOT NULL,
    message CHAR(255),
    image_uri CHAR(255),
    PRIMARY KEY (id)
);