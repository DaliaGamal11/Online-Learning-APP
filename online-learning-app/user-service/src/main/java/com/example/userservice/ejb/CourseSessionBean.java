package com.example.courseservice.ejb;

import com.example.courseservice.entity.Course;
import com.example.courseservice.entity.User;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateful
public class CourseSessionBean {

    @PersistenceContext
    private EntityManager entityManager;

    private Course currentCourse;

    public Course getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(Course currentCourse) {
        this.currentCourse = currentCourse;
    }

    public boolean isAdmin() {
        return getLoggedInUser().getRole() == Role.ADMIN;
    }

    public boolean isInstructor() {
        return getLoggedInUser().getRole() == Role.INSTRUCTOR;
    }

    public User getLoggedInUser() {
        // You can either retrieve the logged-in user from the session context or use a similar approach as in the UserSessionBean
        // For simplicity, I'll assume you have a method to get the logged-in user from the session context
        // ...
        return loggedInUser;
    }

    public Set<Course> getAllCourses() {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c", Course.class);
        return query.getResultList();
    }

    public Course getCourseById(int courseId) {
        return entityManager.find(Course.class, courseId);
    }

    public void createCourse(String name, String duration, String category, Double rating, int capacity, User instructor) {
        Course course = new Course(name, duration, category, rating, capacity, instructor);
        entityManager.persist(course);
    }

    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    public void deleteCourse(int courseId) {
        Course course = getCourseById(courseId);
        if (course != null) {
            entityManager.remove(course);
        }
    }
}