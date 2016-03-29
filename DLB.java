/*DLB data structure, this works correctly.
 */



/*
 * @author drbickford1
 */
public class DLB implements DLBInterface
{
    //public methods
    protected Node root;
    protected Node currNode;
//    protected Node nodeAfter;
    //DLB constructor creating a new node with null values
    public DLB()
    {
        //creating a new root
        root = new Node(' ', false, null, null);
    }
    /*
     *add method. takes a string value and adds it to the DLB
    */
    public boolean add(String s) 
    {
        char[] charArray = s.toCharArray();
        boolean added = false;
        currNode = root;
        Node newNode;
        
        if(root.getDownNode() == null)
        {
            //for loop to create new nodes for the first word added
            for(int i=0; i<charArray.length; i++)
            {
                newNode = new Node(charArray[i], false, null, null);
                if(i == charArray.length-1)
                {
                    newNode.setBool(true);
                }
                currNode.setDownNode(newNode);
                currNode = currNode.getDownNode();
            }
            added = true;
        }
        else
        {
            currNode = currNode.getDownNode();
            for(int i=0; i<charArray.length; i++)
            {
                if(currNode.getData() == charArray[i])
                {
                    if(i == charArray.length - 1)
                    {
                        currNode.setBool(true);
                        added = true;
                    }
                    else if(currNode.getDownNode() == null)
                    {
//                        if(i == charArray.length - 1)
//                        {
//                            currNode.setBool(true);
//                            added = true;
//                        }
//                        else
//                        {
                            newNode = new Node(charArray[i], false, null, null);
                            currNode.setDownNode(newNode);
//                        }
                    }
                    currNode = currNode.getDownNode();
                }
                else if(currNode.getData() != charArray[i] && currNode.getRightNode() != null)
                {
                    //while loop to go through and get the node to the right
                    while(currNode.getData() != charArray[i] && currNode.getRightNode() != null)
                    {
                        currNode = currNode.getRightNode();
                    }
                    if(currNode.getData() != charArray[i] && currNode.getRightNode() == null)
                    {
                        newNode = new Node(charArray[i], false, null, null);
                        currNode.setRightNode(newNode);
                        currNode = currNode.getRightNode();
                        
                        if(i == charArray.length - 1)
                        {
                            currNode.setBool(true);
                            added = true;
                        }
                        else
                        {
                            newNode = new Node(charArray[i+1], false, null, null);
                            currNode.setDownNode(newNode);
                        }
                        currNode = currNode.getDownNode();
                    }
                    else if(currNode.getData() == charArray[i])
                    {
                        if(i == charArray.length - 1)
                        {
                            currNode.setBool(true);
                            added = true;
                        }
                        else if(currNode.getDownNode() == null)
                        {
//                            if(i == charArray.length - 1)
//                            {
//                                currNode.setBool(true);
//                                added = true;
//                            }
//                            else
//                            {
                                newNode = new Node(charArray[i], false, null, null);
                                currNode.setDownNode(newNode);
//                            }
                        }
                        currNode = currNode.getDownNode();
                    }
                }
                else if((currNode.getData() != charArray[i] && currNode.getRightNode() == null))
                {
                    newNode = new Node(charArray[i], false, null, null);
                    currNode.setRightNode(newNode);
                    currNode = currNode.getRightNode();
                    
                    if(i == charArray.length - 1)
                    {
                        currNode.setBool(true);
                        added = true;
                    }
                    else
                    {
                        newNode = new Node(charArray[i+1], false, null, null);
                        currNode.setDownNode(newNode);
                    }
                    currNode = currNode.getDownNode();
                }
            }
        }
        return added;
    }

    /* search method. Takes a stringbuilder and searches the tree for that word
     */
    public boolean search(StringBuilder s)
    {
        //variables used by this method
        boolean found = false;
        String searchString = s.toString();
        char[] searchArray = searchString.toCharArray();
        currNode = root;
        currNode = currNode.getDownNode();
        
        for(int i=0; i<searchArray.length; i++)
        {
            if(currNode.getData() != searchArray[i])
            {
                while(currNode.getData() != searchArray[i])
                {
                    if(currNode.getRightNode() == null)
                    {
                        return found;
                    }
                    currNode = currNode.getRightNode();
//                    if(i == searchArray.length-1 && currNode.getBool())
//                    {
//                        found = true;
//                        return found;
//                    }
                }
                if(i == searchArray.length-1 && currNode.getBool())
                {
                    found = true;
                    return found;
                }
                currNode = currNode.getDownNode();
            }
            else if(currNode.getData() == searchArray[i])
            {
                if(i == searchArray.length-1 && currNode.getBool())
                {
                    found = true;
                    return found;
                }
                else if(currNode.getDownNode() == null)
                {
                    return found;
                }
                currNode = currNode.getDownNode();
            }
        }
        return found;
    }
    
    public void traverse()
    {
        StringBuilder word = new StringBuilder();
        char currentChar = ' ';
        currNode = root;
        Node newNode;
        
        
        
        
        
        
    }
    
    
    /*
     *Node class. creates new nodes with given data and set values.
    */
    protected class Node
    {
      //global values
      protected char data; // entry in node
      protected boolean word; //terminator for each word true if word, false otherwise
      protected Node right; //link to right node
      protected Node down; // link to down node
      
      //Node constructor.
      protected Node(char dataPortion, boolean lastChar)
      {
         this(dataPortion, lastChar, null, null);
      } // end constructor
      //Node constructor setting the data and nodes to valid areas
      protected Node(char dataPortion, boolean isWord, Node downNode, Node rightNode)
      {
         data = dataPortion;
         word = isWord;
         down = downNode;
         right = rightNode;
      } // end constructor
      //returns the data that the node holds
      protected char getData()
      {
         return data;
      } // end getData
      //setting the data the node holds
      protected void setData(char newData)
      {
         data = newData;
      } // end setData
      
      protected boolean getBool()
      {
          return word;
      }
      
      protected void setBool(boolean endWord)
      {
          word = endWord;
      }
      //returning the right node
      protected Node getRightNode()
      {
         return right;
      } // end getNextNode
      //returning the down node
      protected Node getDownNode()
      {
         return down;
      }
      //setting the right node
      protected void setRightNode(Node nextRightNode)
      {
         right = nextRightNode;
      } // end setNextNode
      //setting the down node
      protected void setDownNode(Node nextDownNode)
      {
         down = nextDownNode;
      }
   } // end Node
    
}