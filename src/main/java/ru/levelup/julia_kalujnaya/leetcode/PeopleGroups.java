package ru.levelup.julia_kalujnaya.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Задание "Group the People Given the Group Size They Belong To"
 * (https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/)
 * Задача: по массиву размеров групп, к которым относится каждый i-й человек, необходимо восстановить список
 * участников групп
 *
 * @author Юлия Калюжная
 */
public class PeopleGroups {
    private List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> groupsFull = new ArrayList<>();
        HashMap<Integer, List<Integer>> groupsNotFull = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            int groupSize = groupSizes[i];
            if (groupsNotFull.containsKey(groupSize)) {
                groupsNotFull.get(groupSize).add(i);
                if (groupsNotFull.get(groupSize).size() == groupSize) {
                    groupsFull.add(groupsNotFull.get(groupSize));
                    groupsNotFull.remove(groupSize);
                }
            } else {
                List<Integer> newGroup = new ArrayList<>();
                newGroup.add(i);
                if ((groupSize > 1)) {
                    groupsNotFull.put(groupSize, newGroup);
                } else {
                    groupsFull.add(newGroup);
                }
            }
        }
        return groupsFull;
    }

    public static void main(String[] args) {
        PeopleGroups app = new PeopleGroups();
        InputOutputHelper helper = new InputOutputHelper();
        int[] inputArray = helper.getIntArray();
        helper.printMessageAndArray("Вы ввели массив: ", inputArray);
        helper.printMessageAndList("Ответ: ", app.groupThePeople(inputArray));
    }
}
