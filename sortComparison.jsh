import java.io.*;
import java.util.*;
void sortComparison(String[] filenames) throws IOException{
    ArrayList<String> bb=new ArrayList<>();
    bb.add("bubbleSort");
    for(String filename:filenames){
        bb.add(Long.toString(measureBubbleSort(filename)));}
    ArrayList<String> mer=new ArrayList<>();
    mer.add("mergeSort");
    for(String filename:filenames){
        mer.add(Long.toString(measureMergeSort(filename)));}
    ArrayList<String> head=new ArrayList<>();
    head.add("          ,10,100,10000");
    System.out.println(String.join(",", head));
    System.out.println(String.join(",", bb));
    System.out.println(String.join(",", mer));
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("sortComparison.csv"))) {
        writer.write(String.join(",",head));
        writer.newLine();
        writer.write(String.join(",",bb));
        writer.newLine();
        writer.write(String.join(",",mer));
        System.out.println("已保存");
    } catch (IOException e) {
        e.printStackTrace();}}
int cardCompare(String card1,String card2){
    final String suitPrinciple="HCDS";
    char lastChar1 = card1.charAt(card1.length() - 1);
    int index1 = suitPrinciple.indexOf(lastChar1);
    char lastChar2 = card2.charAt(card2.length() - 1);
    int index2=suitPrinciple.indexOf(lastChar2);
    int first=Integer.compare(index1,index2);
    ArrayList<Integer> res=new ArrayList<>();
    if(first==0){
        int a=Integer.parseInt(card1.substring(0,card1.length()-1));
        int b=Integer.parseInt(card2.substring(0,card2.length()-1));
        return Integer.compare(a,b);
    }else{
        return first;}}
ArrayList<String> bubbleSort(ArrayList<String> array){
    int n=array.size();
    for(int i=0;i<=n-1;i++){
        for(int j=0;j<n-1-i;j++){
            if(cardCompare(array.get(j),array.get(j+1))>0){
                String temp=array.get(j);
                array.set(j,array.get(j+1));
                array.set(j+1,temp);}}}
    return array;}
ArrayList<String> mergeSort(ArrayList<String> array) {
            if (array.size() <= 1) {
                return array; }
            int mid = array.size() / 2;
            ArrayList<String> left = new ArrayList<>(array.subList(0, mid));
            ArrayList<String> right = new ArrayList<>(array.subList(mid, array.size()));
            return merge(mergeSort(left), mergeSort(right));}
private ArrayList<String> merge(ArrayList<String> left, ArrayList<String> right) {
            ArrayList<String> result = new ArrayList<>();
            int i = 0, j = 0;  
            while (i < left.size() && j < right.size()) {
                if (cardCompare(left.get(i), right.get(j)) <= 0) {
                    result.add(left.get(i));
                    i++;
                } else {
                    result.add(right.get(j));
                    j++;}}      
            while (i < left.size()) {
                result.add(left.get(i));
                i++;}
            while (j < right.size()) {
                result.add(right.get(j));
                j++;}
            return result;}
long measureBubbleSort(String filename) throws IOException{
    long start=System.nanoTime();
    List<String> lines = Files.readAllLines(Paths.get(filename));
    ArrayList<String> array=new ArrayList<>();
    for (String line : lines) {
        array.addAll(Arrays.asList(line.split("\\s+")));}
    ArrayList<String> res=bubbleSort(array);
    long end=System.nanoTime();
    return (end-start)/100000;}
long measureMergeSort(String filename) throws IOException{
    long start=System.nanoTime();
    List<String> lines = Files.readAllLines(Paths.get(filename));
    ArrayList<String> array=new ArrayList<>();
    for (String line : lines) {
        array.addAll(Arrays.asList(line.split("\\s+")));}
    ArrayList<String> res=mergeSort(array);
    long end=System.nanoTime();
    return (end-start)/100000;}
