import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerClass <T extends Identifiable<K> & Comparable<T>, K extends Comparable<K>>{
    List<T> entities = new ArrayList<>();
    Set<T> uniqueEntities = new TreeSet<>();
    Map<K,T> entitiesById = new LinkedHashMap<>();

    public boolean addEntity(T entity){
        if (!entity.equals(null)){
            entities.add(entity);
            uniqueEntities.add(entity);
            entitiesById.put(entity.getId(), entity);
            return true;
        }
        return false;
    }

    public boolean removeEntity(K id){
        T toBeRemoved = findInMap(id);
        if (findInMap(id).equals(null)){
            return false;
        }
        else {
            entities.remove(toBeRemoved);
            uniqueEntities.remove(toBeRemoved);
            return true;
        }
    }

    public List<T> getEntitiesSortedNaturally(){
        List natSortedEntities = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++){
            natSortedEntities.add(entities.get(i));
        }
        Collections.sort(natSortedEntities);
        return natSortedEntities;
    }

    public List<T> getEntitiesSortedByName(){
        List nameSortedEntities = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++){
            nameSortedEntities.add(entities.get(i));
        }
        Collections.sort(nameSortedEntities, new nameComparator<>());
        return nameSortedEntities;
    }

    public List<T> getEntitiesSortedBy(Comparator<T> comparator){
        List<T> anyBySortedEntities = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++){
            anyBySortedEntities.add(entities.get(i));
        }
        Collections.sort(anyBySortedEntities, comparator);
        return anyBySortedEntities;
    }

    public T getEntityById(K id){
        if (!id.equals(null)){
            return entitiesById.get(id);
        }
        else {
            return null;
        }
    }

    // needs to be checked if is naturally sorted
    public Set<T> getAllUniqueEntities(){
        return uniqueEntities;
    }

    public int getEntitiesCount(){
        return entities.size();
    }
    // new thing learned: search by a given pattern
    public List<String> findEntitiesByNamePattern(String pattern){
        List<String> namesByPattern = new ArrayList<>();
        Pattern searchPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        for (int i = 0; i < entities.size(); i++){
            String entityName = entities.get(i).getDisplayName();
            Matcher matcher = searchPattern.matcher(entityName);
            if (matcher.find()) {
                namesByPattern.add((String) entityName);
            }
        }
        return namesByPattern;
    }

    public void clearAllEntities(){
        entities.clear();
        uniqueEntities.clear();
        entitiesById.clear();
    }

    //startId and endId are supposed to be not primitive, so how to check if they fit in the range ? with generics, wildcards and in general when 2 objects must
    // compared USE Comparator for custom comparing, Comarable for natural and on site comparing. That;s why using >= and <= are signed as error
    public List<T> getEntitiesByIdRange(K startId, K endId){
        List<T> entitiesByRange = new ArrayList();
        for (Map.Entry<K, T> entry : entitiesById.entrySet()){
            K currentId = entry.getKey();
            T currentEntity = entry.getValue();
            if (currentId.compareTo(startId) >= 0 && currentId.compareTo(endId) <= 0){
                entitiesByRange.add(currentEntity);
            }
        }
        return entitiesByRange;
    }


    public T findInMap(K id){
        T toBeRemoved = null;
        for (Map.Entry<K,T> entry : entitiesById.entrySet()){
            if (entry.getKey().equals(id)){
                toBeRemoved = entry.getValue();
                break;
            }
        }
        return toBeRemoved;
    }

}
