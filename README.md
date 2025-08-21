# Generic Entity Management System
## Overview
This exercise demonstrates advanced Java concepts including Generics, Collections Framework, and Custom Sorting through a flexible entity management system that can handle multiple data types safely and efficiently.
## Learning Objectives

### Bounded Generics: Understanding type constraints with extends keyword.
### Multiple Type Parameters: Working with <T, K> generic declarations.
### Collections Framework: Practical usage of List, Set, and Map interfaces.
### Comparable vs Comparator: Natural ordering vs custom sorting strategies.
### Type Safety: Preventing ClassCastException through compile-time checks.
### Code Reusability: Writing generic code that works with different entity types.

## System Architecture
Core Interface
Identifiable<K>
public interface Identifiable<K> {
    K getId();
    String getDisplayName();
}
## Purpose: Provides a contract for entities that can be uniquely identified and displayed.
## Entity Classes
### 1. Student Class

Implements: Identifiable<Integer>, Comparable<Student>
Fields: id (Integer), name (String), grade (Double), age (Integer)
Natural Ordering: Compare by student ID

### 2. Employee Class

Implements: Identifiable<String>, Comparable<Employee>
Fields: employeeCode (String), name (String), salary (Double), department (String)
Natural Ordering: Compare by employee code

### 3. Product Class

Implements: Identifiable<Long>, Comparable<Product>
Fields: productId (Long), name (String), price (Double), category (String)
Natural Ordering: Compare by product ID

### Generic Manager Class
## Class Declaration
public class ManagerClass<T extends Identifiable<K> & Comparable<T>, K extends Comparable<K>>
## Type Parameters:

T: The entity type (must be identifiable and comparable)
K: The ID type (must be comparable for range operations)

## Internal Collections
private List<T> entities;              // Maintains insertion order
private Set<T> uniqueEntities;         // Ensures uniqueness (use TreeSet for sorting)
private Map<K, T> entitiesById;        // Fast O(1) lookup by ID
## Method Implementation Guide
methods are structired based on CRUD operations
### addEntity(T entity)

Add entity to all three collections
Handle duplicate IDs appropriately
Return success status

### removeEntity(K id)

Remove from all collections using ID
Return boolean indicating success

### getEntityById(K id)

Is demanded to it to take the current entity via provided id. 

## Sorting Operations
### getEntitiesSortedNaturally()

Use entity's compareTo() method
Return new sorted list (don't modify original)

### getEntitiesSortedByName()

Use generic NameComparator<T extends Identifiable<K>>
Sort alphabetically by display name

### getEntitiesSortedBy(Comparator<T> comparator)

Accept any custom comparator
Demonstrate flexibility of generic design

## Search Operations
### findEntitiesByNamePattern(String pattern)

Case-insensitive substring search
Loop through entities list
Use String.contains() method

### getEntitiesByIdRange(K startId, K endId)

Use compareTo() for range checking
Loop through map entries
Add matching entities to result

## Custom Comparators
### Generic Name Comparator
class NameComparator<T extends Identifiable<?>> implements Comparator<T>.

Works with any Identifiable entity, sorts by display name.
Entity-Specific Comparators
### StudentGradeComparator

Primary: Grade (descending - highest first)
Secondary: Name (ascending - alphabetical)

### EmployeeSalaryComparator

Primary: Salary (descending - highest first)
Secondary: Name (ascending - alphabetical)

### ProductPriceComparator

Primary: Price (ascending - lowest first)
Secondary: Name (ascending - alphabetical)

## Implementation Requirements
Entity Classes Must Implement

### Constructor accepting all fields
### Getter methods for all fields
### getId() method returning the unique identifier
### getDisplayName() method returning entity name
### compareTo() method for natural ordering
### toString() method for formatted display

## Manager Class Must Implement

### Constructor initializing all three collections
### All CRUD methods as specified
### All sorting methods as specified
### All search methods as specified
### Utility methods like getEntitiesCount()

## Testing Strategy
Create Three Managers

ManagerClass <Student, Integer> studentManager;

ManagerClass<Employee, String> employeeManager;  

ManagerClass<Product, Long> productManager;

Each Manager has been tested with 5 entities with varied data

## Natural sorting demonstration
### Name-based sorting using generic comparator
### Custom sorting using entity-specific comparators
### ID lookup with different ID types
### Pattern search functionality
### Range queries (if applicable)
### Removal operations and verification

## Edge Cases to Test

Empty pattern search
Non-existent ID lookup
Duplicate ID handling
Range operations with equal start/end values

## Key Design Patterns
### Bounded Generics
<T extends Identifiable<K> & Comparable<T>> ensures T has both identification and comparison capabilities.

### Generic Wildcards
<T extends Identifiable<?>> allows comparator to work with any identifiable type.

### Collection Strategy

List: For ordered access and iteration.

Set: For uniqueness and automatic sorting.

Map: For fast key-based lookup.


### Type Safety

Prevents ClassCastException at runtime
Ensures only compatible types are used together
Compiler catches type mismatches early

### Performance

Generic collections to work with same class "features" but with different types class can hold
HashMap provides O(1) average lookup time instead of List's O(n) 
TreeSet maintains sorted order automatically thanks to Comparable interface each class implements

### Code Reusability

Single manager class handles multiple entity types
Generic comparators work across different entities
Type-safe operations without code duplication
