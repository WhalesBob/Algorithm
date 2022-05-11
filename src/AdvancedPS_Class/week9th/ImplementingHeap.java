package AdvancedPS_Class.week9th;

import java.util.ArrayList;

public class ImplementingHeap {
    private ArrayList<HeapElement> list;
    private boolean increasingOrder;

    public ImplementingHeap(boolean increasingOrder) {
        this.list = new ArrayList<>();
        list.add(new HeapElement());
        this.increasingOrder = increasingOrder;
    }

    void insertInHeap(HeapElement element){ // 힙 내부 삽입 완료.
        list.add(element);
        int child = list.size()-1; int parent = child / 2;
        boolean isValid = increasingOrder ?
                (list.get(parent).compareData >= list.get(child).compareData) :
                (list.get(parent).compareData <= list.get(child).compareData);
        while(isValid && parent != 0){
            swap(parent,child);
            parent /= 2; child /= 2;
            isValid = increasingOrder ?
                    list.get(parent).compareData >= list.get(child).compareData :
                    list.get(parent).compareData <= list.get(child).compareData;
        }
    }

    HeapElement poll(){
        HeapElement output = new HeapElement();
        output.updateData(list.get(1));
        list.get(1).updateData(list.remove(list.size()-1));

        for(int i = 1; i * 2 < list.size()-1;){
            HeapElement parent = list.get(i);
            boolean exit = increasingOrder ?
                    ((parent.compareData < list.get(i*2).compareData) && (parent.compareData < list.get(i*2+1).compareData)) :
                    ((parent.compareData > list.get(i*2).compareData) && (parent.compareData > list.get(i*2+1).compareData));
            boolean compare = increasingOrder ?
                    list.get(i*2).compareData < list.get(i*2+1).compareData : list.get(i*2).compareData > list.get(i*2+1).compareData;

            if(exit){
                break;
            }else if(compare){
                swap(i, i*2);
                i *= 2;
            }else{
                swap(i, i*2+1);
                i = i*2 + 1;
            }
        }
        if(list.size() == 3){
            boolean compare = increasingOrder ?
                    list.get(1).compareData > list.get(2).compareData : list.get(1).compareData < list.get(2).compareData;
            if(compare){
                swap(1,2);
            }
        }
        return output;
    }
    void swap(int parent, int child){
        HeapElement parentElement = list.get(parent);
        HeapElement childElement = list.get(child);
        HeapElement temp = new HeapElement();
        temp.updateData(parentElement);
        parentElement.updateData(childElement);
        childElement.updateData(temp);
    }
    int heapSize(){
        return list.size();
    }
}
class HeapElement{ // 1번 2번에서 둘다 사용성이 높도록 만듬.
    int data;
    int compareData;
    boolean isNumber;

    public HeapElement() {
        this.isNumber = true;
        this.data = 0;
        this.compareData = 0;
    }

    public HeapElement(int data, boolean isNumber) {
        this.data = data;
        this.isNumber = isNumber;
        this.compareData = isNumber ? data : 1;
    }
    void updateData(HeapElement element){
        this.data = element.data;
        this.compareData = element.compareData;
        this.isNumber = element.isNumber;
    }
}
