package com.example.courseservice.ejb;

import com.example.courseservice.entity.Course;
import com.example.courseservice.entity.User;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CourseBean {

    @PersistenceContext
    private EntityManager entityManager;

    // Method to add a course
    public void addCourse(Course course) {
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();
    }

    // Method to delete a course by ID
    public void deleteCourse(int courseId) {
        Course course = getCourseById(courseId);
        if (course != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(course);
            entityManager.getTransaction().commit();
        }
    }

    // Method to update a course
    public void updateCourse(Course updatedCourse) {
        Course existingCourse = getCourseById(updatedCourse.getCourseId());
        if (existingCourse != null) {
            entityManager.getTransaction().begin();
            existingCourse.setName(updatedCourse.getName());
            existingCourse.setDuration(updatedCourse.getDuration());
            existingCourse.setCategory(updatedCourse.getCategory());
            existingCourse.setRating(updatedCourse.getRating());
            existingCourse.setCapacity(updatedCourse.getCapacity());
            existingCourse.setInstructor(updatedCourse.getInstructor());
            entityManager.merge(existingCourse);
            entityManager.getTransaction().commit();
        }
    }

    // Method to get all courses
    public List<Course> getAllCourses() {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c", Course.class);
        return query.getResultList();
    }

    // Method to get a course by ID
    public Course getCourseById(int courseId) {
        return entityManager.find(Course.class, courseId);
    }

    // Method to get a course by name
    public Course getCourseByName(String name) {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c WHERE c.name = :name", Course.class);
        query.setParameter("name", name);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // or handle this case appropriately
        }
    }

    // Method to get courses by instructor
    public List