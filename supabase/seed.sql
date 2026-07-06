-- =============================================
-- SEED DATA — deodorant-reborn-api
-- Données de test pour le développement local
-- =============================================

-- Feedback
INSERT INTO feedback (email, message, rating) VALUES
  ('developer@example.com', 'Super plugin! La détection Type-1 fonctionne parfaitement.', 5),
  ('researcher@uni.de', 'Très utile pour ma thèse sur la qualité du code Java.', 4),
  ('student@example.com', 'Facile à installer et à utiliser sur IntelliJ 2024.', 5),
  (null, 'La détection Type-2 est impressionnante, bon travail!', 4),
  ('dev2@example.com', 'Quelques faux positifs sur les clones Type-2 mais globalement excellent.', 3);

-- Feature Requests
INSERT INTO feature_requests (title, description, email, votes) VALUES
  (
    'Support Kotlin',
    'Ajouter la détection de clones dans les fichiers Kotlin en plus de Java.',
    'kotlin-dev@example.com',
    12
  ),
  (
    'Export rapport PDF',
    'Pouvoir exporter la liste des clones détectés en format PDF ou CSV.',
    null,
    8
  ),
  (
    'Mode batch',
    'Analyser plusieurs projets en même temps depuis la ligne de commande.',
    'ci-user@example.com',
    6
  ),
  (
    'Intégration SonarQube',
    'Connecter le plugin avec SonarQube pour une analyse complète.',
    'sonar@example.com',
    15
  ),
  (
    'Support Type-3 clones',
    'Étendre la détection aux clones de Type-3 (modifications partielles).',
    'researcher@uni.de',
    20
  );

-- Plugin Downloads
INSERT INTO plugin_downloads (version) VALUES
  ('v1.0.0'),
  ('v1.0.0'),
  ('v1.0.0'),
  ('v1.0.1'),
  ('v1.0.1'),
  ('v1.0.2');