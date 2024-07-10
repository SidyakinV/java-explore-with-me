CREATE TABLE IF NOT EXISTS users (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(254) NOT NULL,
  CONSTRAINT PK_USER PRIMARY KEY (id),
  CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS categories (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(50) NOT NULL,
  CONSTRAINT PK_CATEGORY PRIMARY KEY (id),
  CONSTRAINT UQ_CATEGORY_NAME UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS events (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  cat_id BIGINT NOT NULL,
  initiator_id BIGINT NOT NULL,
  title VARCHAR(120) NOT NULL,
  annotation VARCHAR(2000) NOT NULL,
  description VARCHAR(7000) NOT NULL,
  event_date TIMESTAMP NOT NULL,
  location_lat FLOAT NOT NULL,
  location_lon FLOAT NOT NULL,
  paid BOOLEAN NOT NULL,
  participant_limit INTEGER NOT NULL,
  request_moderation BOOLEAN NOT NULL,
  confirmed_requests BIGINT NOT NULL,
  created TIMESTAMP NOT NULL,
  published TIMESTAMP,
  views BIGINT NOT NULL DEFAULT(0),
  state VARCHAR(20) NOT NULL,
  CONSTRAINT PK_EVENT PRIMARY KEY (id),
  FOREIGN KEY (cat_id) REFERENCES categories(id) ON UPDATE CASCADE,
  FOREIGN KEY (initiator_id) REFERENCES users(id) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS requests (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  event_id BIGINT NOT NULL,
  requester_id BIGINT NOT NULL,
  created TIMESTAMP NOT NULL,
  status VARCHAR(20) NOT NULL,
  CONSTRAINT PK_REQUEST PRIMARY KEY (id),
  FOREIGN KEY (event_id) REFERENCES events(id) ON UPDATE CASCADE,
  FOREIGN KEY (requester_id) REFERENCES users(id) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS compilations (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  title VARCHAR(50) NOT NULL,
  pinned BOOLEAN NOT NULL,
  CONSTRAINT PK_COMPILATION PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS compilation_events (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  compilation_id BIGINT NOT NULL,
  event_id BIGINT NOT NULL,
  CONSTRAINT PK_COMPILATION_EVENT PRIMARY KEY (id),
  FOREIGN KEY (compilation_id) REFERENCES compilations(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (event_id) REFERENCES events(id) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS log_ip (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  ip VARCHAR(20) NOT NULL,
  path VARCHAR(500) NOT NULL,
  first_time TIMESTAMP NOT NULL,
  last_time TIMESTAMP NOT NULL,
  view_count BIGINT NOT NULL,
  CONSTRAINT PK_LOG_IP PRIMARY KEY (id)
);
