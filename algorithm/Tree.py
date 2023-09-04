class Node:
    def __init__(self, value=None, lvalue=None, rvalue=None):
        self.value = value
        self.left = lvalue
        self.right = rvalue


class Tree:
    def __init__(self, root=None):
        self.root = root

    # 삽입
    def push(self, value):
        """
        1. root node가 없을 경우 root로 지정
        2. root node가 있을 경우
            2-1. root보다 작으면 left에 추가
            2-2. root보다 크면 right에 추가
        """
        node = Node(value=value)
        tempNode = self.root

        # 1
        if self.root == None:
            self.root = node
            return

        # 2
        ptrNode = self.root
        while ptrNode is not None:
            tempNode = ptrNode
            if node.value < tempNode:
                ptrNode = ptrNode.left
            else:
                ptrNode = ptrNode.right

        # 2-1
        if node.value < tempNode.value:
            tempNode.left = node
        # 2-2
        else:
            tempNode.right = node


if __name__ == "__main__":
    tree = Tree()
