package com.example.courseservice.entity;

import com.example.userservice.entity.Role;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int courseId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "number_of_enrolled_students")
    private int numberOfEnrolledStudents;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor;

    // Default constructor
    public Course() {}

    // Parameterized constructor for all fields
    public Course(String name, String duration, String category, Double rating, int capacity, User instructor) {
        this.name = name;
        this.duration = duration;
        this.category = category;
        this.rating = rating;
        this.capacity = capacity;
        this.instructor = instructor;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(int enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }


    // toString method
    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", category='" + category + '\'' +
                ", rating=" + rating +
                ", capacity=" + capacity +
                ", numberOfEnrolledStudents=" + numberOfEnrolledStudents +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}