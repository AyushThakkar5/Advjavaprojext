<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Sign up Form</title>
    <style>
        body, html {
            height: 100vh;
            /* margin: 0; */
            box-sizing : border-box;
        }
        .bg-dark {
            background-color: #343a40 !important;
        }
        .card-registration {
            border-radius: 0.25rem;
        }
        .form-outline {
            margin-bottom: 1.5rem;
            position: relative;
        }
        .error-message {
            color: #dc3545;
            font-size: 0.875rem;
            margin-top: 0.25rem;
            display: block;
        }
        .form-outline label {
            pointer-events: none;
            font-size: 0.9rem;
            margin-bottom: 0.3rem;
            display: block;
        }
        .password-requirements {
            font-size: 0.75rem;
            color: #6c757d;
            margin-top: 0.25rem;
        }
        .invalid-feedback {
            display: block;
        }
    </style>
</head>
<body>
    <section class="h-100 bg-dark">
        <div class="container d-flex justify-content-center align-items-center">
            <div class="card card-registration">
                <div class="row g-0">
                    <div class="col-xl-6 d-none d-xl-block">
                        <img src="stefan-stefancik-pzA7QWNCIYg-unsplash.jpg" alt="Sample photo" class="img-fluid" 
                             style="border-top-left-radius: .25rem; border-bottom-left-radius: .25rem;" />
                    </div>
                    <div class="col-xl-6">
                        <div class="card-body p-md-5 text-black">
                            <h3 class="mb-5 text-uppercase">Sign up Form</h3>

                            <form action="SignupFormservlet" method="post" class="needs-validation" novalidate>
                                <!-- Username Field -->
                                <div class="form-outline">
                                    <label class="form-label" for="username">Username</label>
                                    <input type="text" id="username" name="username" 
                                           class="form-control form-control-lg ${not empty usernameError ? 'is-invalid' : ''}" 
                                           value="<c:out value='${username}'/>" 
                                           placeholder="Enter username" />
                                    <div class="invalid-feedback" id="username-error">
                                        <c:out value="${usernameError}"/>
                                    </div>
                                </div>

                                <!-- Phone Field -->
                                <div class="form-outline">
                                    <label class="form-label" for="phone">Phone No</label>
                                    <input type="text" id="phone" name="phone" 
                                           class="form-control form-control-lg ${not empty phoneError ? 'is-invalid' : ''}" 
                                           value="<c:out value='${phone}'/>" 
                                           placeholder="Enter 10-digit phone number" />
                                    <div class="invalid-feedback" id="phone-error">
                                        <c:out value="${phoneError}"/>
                                    </div>
                                </div>

                                <!-- Birthdate Field -->
                                <div class="form-outline">
                                    <label class="form-label" for="birthdate">Birth Date</label>
                                    <input type="date" id="birthdate" name="birthdate" 
                                           class="form-control form-control-lg ${not empty birthdateError ? 'is-invalid' : ''}" 
                                           value="<c:out value='${birthdate}'/>" />
                                    <div class="invalid-feedback" id="birthdate-error">
                                        <c:out value="${birthdateError}"/>
                                    </div>
                                </div>

                                <!-- Email Field -->
                                <div class="form-outline">
                                    <label class="form-label" for="email">Email ID</label>
                                    <input type="email" id="email" name="email" 
                                           class="form-control form-control-lg ${not empty emailError ? 'is-invalid' : ''}" 
                                           value="<c:out value='${email}'/>" 
                                           placeholder="Enter email address"/>
                                    <div class="invalid-feedback" id="email-error">
                                        <c:out value="${emailError}"/>
                                    </div>
                                </div>

                                <!-- Gender Field -->
                                <div class="d-md-flex justify-content-start align-items-center mb-4 py-2">
                                    <h6 class="mb-0 me-4">Gender: </h6>
                                    <div class="form-check form-check-inline mb-0 me-4">
                                        <input class="form-check-input" type="radio" name="gender" id="femaleGender" 
                                               value="female" ${gender eq 'female' ? 'checked' : ''} />
                                        <label class="form-check-label" for="femaleGender">Female</label>
                                    </div>
                                    <div class="form-check form-check-inline mb-0 me-4">
                                        <input class="form-check-input" type="radio" name="gender" id="maleGender" 
                                               value="male" ${gender eq 'male' ? 'checked' : ''} />
                                        <label class="form-check-label" for="maleGender">Male</label>
                                    </div>
                                    <div class="form-check form-check-inline mb-0">
                                        <input class="form-check-input" type="radio" name="gender" id="otherGender" 
                                               value="other" ${gender eq 'other' ? 'checked' : ''} />
                                        <label class="form-check-label" for="otherGender">Other</label>
                                    </div>
                                </div>
                                <div class="invalid-feedback d-block" id="gender-error">
                                    <c:out value="${genderError}"/>
                                </div>

                                <!-- Password Field -->
                                <div class="form-outline">
                                    <label class="form-label" for="password">Password</label>
                                    <input type="password" id="password" name="password" 
                                           class="form-control form-control-lg ${not empty passwordError ? 'is-invalid' : ''}" 
                                           placeholder="Enter password"/>
                                    <div class="password-requirements">
                                        Password must be at least 8 characters long and contain uppercase, lowercase, 
                                        number, and special character.
                                    </div>
                                    <div class="invalid-feedback" id="password-error">
                                        <c:out value="${passwordError}"/>
                                    </div>
                                </div>

                                <div class="d-flex justify-content-center pt-3">
                                    <button type="submit" class="btn btn-warning btn-lg ms-2">Submit form</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- <script>
        document.addEventListener('DOMContentLoaded', function() {
            const form = document.querySelector('form');
            
            form.addEventListener('submit', function(event) {
                let isValid = true;
                
                // Clear previous error styles
                form.querySelectorAll('.is-invalid').forEach(element => {
                    element.classList.remove('is-invalid');
                });

                // Username validation
                const username = form.querySelector('#username');
                if (!username.value.trim() || username.value.length < 3) {
                    username.classList.add('is-invalid');
                    document.getElementById('username-error').textContent = 'Username must be at least 3 characters long';
                    isValid = false;
                }

                // Phone validation
                const phone = form.querySelector('#phone');
                if (!phone.value.trim() || !/^[0-9]{10}$/.test(phone.value)) {
                    phone.classList.add('is-invalid');
                    document.getElementById('phone-error').textContent = 'Please enter a valid 10-digit phone number';
                    isValid = false;
                }

                // Email validation
                const email = form.querySelector('#email');
                const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
                if (!email.value.trim() || !emailRegex.test(email.value)) {
                    email.classList.add('is-invalid');
                    document.getElementById('email-error').textContent = 'Please enter a valid email address';
                    isValid = false;
                }

                // Password validation
                const password = form.querySelector('#password');
                if (!password.value || password.value.length < 8) {
                    password.classList.add('is-invalid');
                    document.getElementById('password-error').textContent = 'Password must be at least 8 characters long';
                    isValid = false;
                }

                if (!isValid) {
                    event.preventDefault();
                }
            });
        });
    </script>
 -->
</body>
</html>
