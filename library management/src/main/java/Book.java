public class Book {
        private int id;
        private String title;
        private String publicationDate;

        public Book(int id, String title, String publicationDate) {
            this.id = id;
            this.title = title;
            this.publicationDate = publicationDate;
        }

        // Getters (optional, for Gson)
        public int getId() { return id; }
        public String getTitle() { return title; }
        public String getPublicationDate() { return publicationDate; }
}